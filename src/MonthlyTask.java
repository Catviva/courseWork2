import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(int idGenerator, String title, Type type,
                       int id, LocalDateTime dateTime, String description)
            throws IncorrectArgumentException {
        super(idGenerator, title, type, id, dateTime, description);
    }
    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getDateTime().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }
}
