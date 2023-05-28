package exceptions;

public class IncorrectArgumentException extends RuntimeException{
    public IncorrectArgumentException(String некорректный_ввод) {
    }

    @Override
    public String toString() {
        return "exceptions.IncorrectArgumentException{} " + super.toString();
    }
}
