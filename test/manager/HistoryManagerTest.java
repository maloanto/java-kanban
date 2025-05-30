package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryManagerTest {
    static TaskManager taskManager;
    static HistoryManager historyManager;
    Task task1 = new Task("Task1", "desc", Status.NEW);
    SubTask subTask1 = new SubTask("SubTask1", "desc", Status.NEW);
    SubTask subTask2 = new SubTask("SubTask2", "desc", Status.NEW);
    SubTask subTask3 = new SubTask("SubTask3", "desc", Status.NEW);
    Epic epic1 = new Epic("Epic1", "desc");

    @BeforeAll
    public static void createTaskManager() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @AfterEach
    public void deleteHistory() {
        historyManager.deleteHistory();
    }

    @Test
    public void historyListShouldBeContainsOneTask() {
        taskManager.createTask(task1);
        taskManager.getTask(task1.getId());
        LinkedList<Task> expectedList = historyManager.getHistory();
        assertEquals(expectedList.size(), 1, "Расмер списка не равен 1");
    }

    @Test
    public void historyListShouldBeContainsTenTasks() {
        taskManager.createTask(task1);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.createEpic(epic1);
        taskManager.addSubtaskInEpic(epic1, subTask1);
        taskManager.addSubtaskInEpic(epic1, subTask2);
        taskManager.addSubtaskInEpic(epic1, subTask3);

        taskManager.getTask(task1.getId());
        taskManager.getSubTask(subTask1.getId());
        taskManager.getSubTask(subTask2.getId());
        taskManager.getSubTask(subTask3.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getSubTask(subTask1.getId());
        taskManager.getSubTask(subTask2.getId());
        taskManager.getSubTask(subTask3.getId());
        taskManager.getEpic(epic1.getId());
        LinkedList<Task> expectedList = historyManager.getHistory();

        assertEquals(expectedList.size(), 10, "Расмер списка не равен 10");
    }

    @Test
    public void shouldLimitListSizeTo10WhenAddingMoreElements() {
        taskManager.createTask(task1);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.createEpic(epic1);
        taskManager.addSubtaskInEpic(epic1, subTask1);
        taskManager.addSubtaskInEpic(epic1, subTask2);
        taskManager.addSubtaskInEpic(epic1, subTask3);

        taskManager.getTask(task1.getId());
        taskManager.getSubTask(subTask1.getId());
        taskManager.getSubTask(subTask2.getId());
        taskManager.getSubTask(subTask3.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getSubTask(subTask1.getId());
        taskManager.getSubTask(subTask2.getId());
        taskManager.getSubTask(subTask3.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.getTask(task1.getId());
        taskManager.getSubTask(subTask1.getId());
        taskManager.getSubTask(subTask2.getId());
        taskManager.getSubTask(subTask3.getId());
        taskManager.getEpic(epic1.getId());

        LinkedList<Task> expectedList = historyManager.getHistory();
        assertEquals(expectedList.size(), 10, "Расмер списка не равен 10");
    }
}
