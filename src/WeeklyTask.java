import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(int idGenerator, String title, Type type,
                      int id, LocalDateTime dateTime, String description) {
        super(idGenerator, title, type, id, dateTime, description);
    }
}
