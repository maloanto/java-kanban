package tasks;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> subTaskId = new ArrayList<>();

    public Epic(String title, String desc, Status status) {
        super(title, desc, status);
    }

    public Epic(String title, String desc) {
        super(title, desc);
    }

    public ArrayList<Integer> getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(ArrayList<Integer> subTaskId) {
        this.subTaskId = subTaskId;
    }
}
