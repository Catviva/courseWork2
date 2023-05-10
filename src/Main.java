import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Выберите задачу");
                printTask();
                if (scanner.hasNextInt()){
                    int task = scanner.nextInt();
                    switch (task) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.removeTask(scanner);
                            break;
                        case 3:
                            TaskService.getAllByDate(scanner);
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите задачу");
                }
            }
        }
    }
    private static void addTask(){

    }
    private static void printTask() {
        System.out.println("Добавить задачу. Удалить задачу. Выбрать задачу по дате.");
    }
}