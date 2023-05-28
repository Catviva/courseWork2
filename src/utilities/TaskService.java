package utilities;

import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundException;
import tasks.*;

import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {

    private static Map<Integer, Task> taskMap = new HashMap<>();

    private static List <Task> removedTasksList = new ArrayList <>();


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
            scanner.nextLine();
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

            createTask(occurance, title, description, type, eventDate);

            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());

        } catch (DateTimeParseException e) {
            System.out.println("Неподходящий формат");
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printCurrentTasks();
        try {
            System.out.println("Для удаления введите Id задачи\n");
            int id = scanner.nextInt();
            if (taskMap.containsKey(id)) {
                Task removedTask = taskMap.get(id);
                removedTasksList.add(removedTask);
                taskMap.remove(id);
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
            List<Task> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Task task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy и попробуйте еще раз.");
        }
        scanner.nextLine();
        System.out.println("Для выхода  нажмите Enter\n");

    }

    public static void printRemovedTasks() {
        for (Task task : removedTasksList) {
            System.out.println(task);

        }
    }


    private static List<Task> findTasksByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task currentTask : taskMap.values()) {
            if (currentTask.getDateTime().toLocalDate().equals(date)) {
                tasks.add(currentTask);
            }
        }
        return tasks;
    }

    private static void createTask(int occurance,
                                   String title,
                                   String description,
                                   Type type,
                                   LocalDateTime localDateTime)
            throws IncorrectArgumentException {
    switch (occurance) {
        case 0 : {
                OneTimeTask oneTimeTask = new OneTimeTask (title, description, type, localDateTime);
                taskMap.put(oneTimeTask.getId(), oneTimeTask);
                break;
            }
        case 1 : {
                DailyTask task = new DailyTask (title, description, type, localDateTime);
                taskMap.put(task.getId(), task);
                break;

            }
        case 2 : {
                WeeklyTask task = new WeeklyTask(title, description, type, localDateTime);
                taskMap.put(task.getId(), task);
                break;

            }
        case 3 : {
                MonthlyTask task = new MonthlyTask(title, description, type, localDateTime);
                taskMap.put(task.getId(), task);
                break;
            }
        case 4 : {
                YearlyTask task = new YearlyTask(title, description, type, localDateTime);
                taskMap.put(task.getId(), task);
                break;
            }

        };
    }

    private static void printCurrentTasks() {
        for (Task taskEntry : taskMap.values()) {
            System.out.println(taskEntry);
        }
    }


}
