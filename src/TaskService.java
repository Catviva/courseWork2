import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {

    private static Map<Integer, Task> taskMap = new HashMap<>();

    private static List<Task> removedTasks = new ArrayList <>();

    public static void addTask(Scanner scanner) {

        try {
            scanner.nextLine();
            System.out.print("Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println(" Введите тип задачи: 0 - Рабочая 1 - Личная");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи:  0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, type, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createEvent(Scanner scanner, String title, String description,
                                    Type type, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repeatable task = null;
            try {
                task = createTask(occurance, title, description, type, eventDate);
                System.out.println("Создана задача " + task);
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }

        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.MM.yyyy HH:mm и попробуйте еще раз");
            createEvent(scanner, title, description, type, occurance);
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTasks();
        try {
            System.out.println("Для удаления введите Id задачи\n");
            int id = scanner.nextInt();
            if (taskMap.containsKey(id)) {
                Repeatable removedTask = taskMap.get(id);
                removedTask.setArchived(true);
                archivedTasks.put(id, removedTask);
                System.out.println("Задача " + id + " удалена\n");
            } else {
                throw new TaskNotFoundException();
            }
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println("Такой задачи не существует\n");
        }

    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату как dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy и попробуйте еще раз.");
        }
        scanner.nextLine();
        System.out.println("Для выхода  нажмите Enter\n");

    }

    public static void printArchivedTasks() {
        for (Repeatable task : archivedTasks.values()) {
            System.out.println(task);
        }
    }

    public static void getGroupedByDate() {
        Map<LocalDate, ArrayList<Repeatable>> taskMap = new HashMap<>();

        for (Map.Entry<Integer, Repeatable> entry : taskMap.entrySet()) {
            Repeatable task = entry.getValue();
            LocalDate localDate = task.getFirstDate().toLocalDate();
            if (taskMap.containsKey(localDate)) {
                ArrayList<Repeatable> tasks = taskMap.get(localDate);
                tasks.add(task);
            } else {
                taskMap.put(localDate, new ArrayList<>(Collections.singletonList(task)));
            }
        }
        for (Map.Entry<LocalDate, ArrayList<Repeatable>> taskEntry : taskMap.entrySet()) {
            System.out.println(taskEntry.getKey() + " : " + taskEntry.getValue());
        }
    }

    private static List<Repeatable> findTasksByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.checkOccurance(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static Repeatable createTask(int occurance, String title, String description,
                                         Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        return switch (occurance) {
            case 0 -> {
                OneTimeTask oneTimeTask = new OneTimeTask (title, description, type, localDateTime);
                taskMap.put(oncelyTask.getId(), oncelyTask);
                yield oneT;
            }
            case 1 -> {
                DailyTask task = new DailyTask (title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    private static void printActualTasks() {
        for (Repeatable task : actualTasks.values()) {
            System.out.println(task);
        }
    }

    public static void removeTask(Scanner scanner) {
    }

    public static void getAllByDate(Scanner scanner) {

    }
}
