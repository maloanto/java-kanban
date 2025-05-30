package manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    SubTask subTask1 = new SubTask("SubTask1", "desc", Status.NEW);
    SubTask subTask2 = new SubTask("SubTask2", "desc", Status.NEW);
    SubTask subTask3 = new SubTask("SubTask3", "desc", Status.NEW);
    Epic epic1 = new Epic("Epic1", "desc");
    Epic epic2 = new Epic("Epic2", "desc");
    Epic epic3 = new Epic("Epic3", "desc");
    static TaskManager taskManager;

    @BeforeAll
    public static void createTaskManager() {
        taskManager = Managers.getDefault();
    }

    @Test
    void subTaskShouldBeAddedInEpic() {
        taskManager.createSubTask(subTask1);
        taskManager.createEpic(epic1);
        taskManager.addSubtaskInEpic(epic1, subTask1);

        int expectedEpicId = epic1.getId();
        int actualEpicIdInSubTask = subTask1.getEpicId();

        assertEquals(expectedEpicId, actualEpicIdInSubTask, "Сабтаска не добавлена в эпик");
    }

    @Test
    void shouldReturnAllTasksAddedToManager() {
        Task task1 = new Task("Task1", "desk1", Status.NEW);
        Task task2 = new Task("Task2", "desk2", Status.NEW);
        Task task3 = new Task("Task3", "desk3", Status.NEW);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        ArrayList<Task> expectedTasksList = new ArrayList<>(List.of(task1, task2, task3));
        ArrayList<Task> actualTasksList = taskManager.getTasks();

        assertEquals(expectedTasksList, actualTasksList, "Списки задач не равны");
    }

    @Test
    void shouldReturnAllEpicsAddedToManager() {
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createEpic(epic3);

        ArrayList<Epic> expectedEpicsList = new ArrayList<>(List.of(epic1, epic2, epic3));
        ArrayList<Epic> actualEpicsList = taskManager.getEpics();

        assertEquals(expectedEpicsList, actualEpicsList, "Списки эпиков не равны");
    }

    @Test
    void shouldReturnAllSubTasksAddedToManager() {
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);

        ArrayList<SubTask> expectedSubTasksList = new ArrayList<>(List.of(subTask1, subTask2, subTask3));
        ArrayList<SubTask> actualSubTaskList = taskManager.getSubTasks();

        assertEquals(expectedSubTasksList, actualSubTaskList, "Списки подзадач не равны");
    }

    @Test
    void shouldReturnAllSubTasksAddedToEpic() {
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);

        taskManager.createEpic(epic1);

        taskManager.addSubtaskInEpic(epic1, subTask1);
        taskManager.addSubtaskInEpic(epic1, subTask2);
        taskManager.addSubtaskInEpic(epic1, subTask3);

        ArrayList<SubTask> expectedSubTasksList = new ArrayList<>(List.of(subTask1, subTask2, subTask3));
        ArrayList<SubTask> actualSubTaskList = taskManager.getAllSubTaskByEpic(epic1);

        assertEquals(expectedSubTasksList, actualSubTaskList, "Списки подзадач не равны");
    }
}
