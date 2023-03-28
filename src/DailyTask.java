import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(int idGenerator, String title, Type type,
                     int id, LocalDateTime dateTime, String description) {
        super(idGenerator, title, type, id, dateTime, description);
    }
}
