public class IncorrectArgumentException extends RuntimeException{
    public IncorrectArgumentException() {
    }

    @Override
    public String toString() {
        return "IncorrectArgumentException{} " + super.toString();
    }
}
