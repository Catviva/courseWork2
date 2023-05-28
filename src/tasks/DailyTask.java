package tasks;

import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }


    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getDateTime().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }
}
