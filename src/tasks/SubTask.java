package tasks;

public class SubTask extends Task{
    private int epicId;

    public SubTask(String title, String desc, Status status) {
        super(title, desc, status);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId=" + epicId +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
