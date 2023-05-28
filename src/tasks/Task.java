package tasks;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private static int idGenerator = 1;

    private final int id;
    private String title;
    private final Type type;
    private final LocalDateTime dateTime;
    private String description;


    public Task(String title,
                String description,
                Type type,
                LocalDateTime dateTime)  {
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        id = idGenerator;
        idGenerator++;

    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean checkOccurance (LocalDateTime requestedDate) {
        return getDateTime().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && title.equals(task.title) && type == task.type && dateTime.equals(task.dateTime) && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, dateTime, description);
    }

    @Override
    public String toString() {
        return id + "." +
                title + " " +
                 type.getTranslation() + " " +
                 dateTime + " " +
                 description;
    }


}
