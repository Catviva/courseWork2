import java.util.*;

public class TaskService {

    private static Map<Integer, Task> taskMap = new HashMap<>();

    private static List<Task> removedTasks = new ArrayList <>();

    public static void addTask (Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println();
        }catch (IncorrectArgumentException e) {
            System.out.println("Попробуйте еще раз");
            addTask(scanner);
        }
    }

    public static void removeTask (Scanner scanner) {
        System.out.println("Введите id заадачи");
        int id = scanner.nextInt();
        if (taskMap.containsKey(id)) {
            removeTask(scanner);
        }else {
        }
        System.out.println("Такой id не существует");

    }
    public static void getAllByDate (Scanner scanner){

    }
}
