import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        SubTask subTask1 = new SubTask("subtask1", "desk1", Status.NEW);
        SubTask subTask2 = new SubTask("subtask2", "desk2", Status.NEW);
        SubTask subTask3 = new SubTask("subtask3", "desk3", Status.NEW);
        SubTask subTask4 = new SubTask("subtask4", "desk4", Status.NEW);
        SubTask subTask5 = new SubTask("subtask5", "desk5", Status.NEW);
        SubTask subTask6 = new SubTask("subtask6", "desk6", Status.NEW);

        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.createSubTask(subTask4);
        taskManager.createSubTask(subTask5);
        taskManager.createSubTask(subTask6);

        Epic epic = new Epic("epic1", "descEpic");
        taskManager.createEpic(epic);

        taskManager.addSubtaskInEpic(epic, subTask1);
        taskManager.addSubtaskInEpic(epic, subTask2);
        taskManager.addSubtaskInEpic(epic, subTask3);
        taskManager.addSubtaskInEpic(epic, subTask4);
        taskManager.addSubtaskInEpic(epic, subTask5);
        taskManager.addSubtaskInEpic(epic, subTask6);


        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTasks());


        taskManager.updateSubTask(1, new SubTask("subtaskUpdate", "desk", Status.IN_PROGRESS));
        System.out.println(taskManager.getEpics());

        System.out.println("---------------------------------------------------------------------------------");


        taskManager.getSubTask(1);
        taskManager.getSubTask(1);
        taskManager.getSubTask(1);
        taskManager.getSubTask(2);
        taskManager.getSubTask(2);
        taskManager.getSubTask(2);
        taskManager.getSubTask(3);
        taskManager.getSubTask(4);
        taskManager.getSubTask(5);
        taskManager.getEpic(7);
        taskManager.getEpic(7);
        taskManager.getEpic(7);
        taskManager.getEpic(7);

        LinkedList<Task> history = historyManager.getHistory();

        for (int i = 0; i < history.size(); i++) {
            System.out.println(i + 1 + "." + history.get(i));
        }

        taskManager.deleteSubTasks();

        System.out.println("Сабтаски:" + taskManager.getSubTasks());
        System.out.println("Сабтаски в эпике: " + epic.getSubTaskId());
    }
}
