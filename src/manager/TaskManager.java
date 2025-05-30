package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;

public interface TaskManager {
    int createTask(Task task);

    int createSubTask(SubTask subTask);

    int createEpic(Epic epic);

    ArrayList<Task> getTasks();

    ArrayList<SubTask> getSubTasks();

    ArrayList<Epic> getEpics();

    void deleteTasks();

    void deleteSubTasks();

    void deleteEpics();

    Task getTask(int id);

    SubTask getSubTask(int subTaskId);

    Epic getEpic(int epicId);

    void updateTask(int id, Task task);

    void addSubtaskInEpic(Epic epic, SubTask subTask);

    void updateSubTask(int id, SubTask subTask);

    void updateEpic(int id, Epic epic);

    void deleteTask(int id);

    void deleteSubTask(int id);

    void deleteEpic(int id);

    ArrayList<SubTask> getAllSubTaskByEpic(Epic epic);
}
