import utilities.TaskService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Выберите задачу:");
                printTask();
                    int action = scanner.nextInt();
                    switch (action) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.deleteTask(scanner);
                            break;
                        case 3:
                            TaskService.getTasksByDay(scanner);
                            break;
                    }
                }
            }
        }
    private static void printTask() {

        System.out.println("Добавить  - 1. \nУдалить  - 2. \nВыбрать по дате - 3.");
    }
}