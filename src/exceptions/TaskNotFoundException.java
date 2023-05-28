package exceptions;

public class TaskNotFoundException extends Exception {
    @Override
    public String toString() {
        return "exceptions.TaskNotFoundException{} " + super.toString();
    }

    public TaskNotFoundException() {
    }
}
