package tasks;

import java.util.Objects;

public class Task {
    protected String title;
    protected String desc;
    protected int id;
    protected Status status;

    public Task(String title, String desc, Status status) {
        this.title = title;
        this.desc = desc;
        this.status = status;
    }

    public Task(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(desc, task.desc) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, desc, id, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
