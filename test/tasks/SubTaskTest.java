package tasks;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubTaskTest {

    static TaskManager taskManager;
    Epic epic1 = new Epic("Epic1", "desc");
    SubTask subTask1 = new SubTask("SubTask1", "desc", Status.NEW);
    SubTask subTask2 = new SubTask("SubTask2", "desc", Status.NEW);
    SubTask subTask3 = new SubTask("SubTask3", "desc", Status.NEW);

    @BeforeAll
    public static void createTaskManager() {
        taskManager = Managers.getDefault();
    }

    @Test
    void subTaskShouldBeCreate() {
        SubTask subTask = new SubTask("SubTask", "desk", Status.NEW);
        taskManager.createSubTask(subTask);
        assertEquals(subTask, taskManager.getSubTask(subTask.getId()));
    }

    @Test
    void subTaskShouldBeUpdate() {
        Epic epic = new Epic("Epic", "desc");
        SubTask subTask = new SubTask("SubTask", "desc", Status.NEW);
        taskManager.createSubTask(subTask);
        taskManager.createEpic(epic);
        taskManager.addSubtaskInEpic(epic, subTask);

        SubTask updateSubTask = new SubTask("SubTaskUpdate", "descUpdate", Status.DONE);
        taskManager.updateSubTask(subTask.getId(), updateSubTask);

        assertEquals(updateSubTask, taskManager.getSubTask(subTask.getId()), "Подзадача не изменилась");
    }

    @Test
    void oneSubTaskShouldBeDelete() {
        taskManager.createSubTask(subTask1);
        taskManager.createEpic(epic1);
        taskManager.addSubtaskInEpic(epic1, subTask1);

        taskManager.deleteSubTask(subTask1.getId());

        assertNull(taskManager.getSubTask(subTask1.getId()));
        assertTrue(taskManager.getAllSubTaskByEpic(epic1).isEmpty());
    }

    @Test
    void allSubTasksShouldBeDelete() {
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);

        taskManager.createEpic(epic1);
        taskManager.addSubtaskInEpic(epic1, subTask1);
        taskManager.addSubtaskInEpic(epic1, subTask2);
        taskManager.addSubtaskInEpic(epic1, subTask3);

        taskManager.deleteSubTasks();

        assertTrue(taskManager.getSubTasks().isEmpty());
        assertTrue(taskManager.getAllSubTaskByEpic(epic1).isEmpty());
    }
}
