package tasks;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    static TaskManager taskManager;
    Epic epic1 = new Epic("Epic1", "desc");
    Epic epic2 = new Epic("Epic2", "desc");
    Epic epic3 = new Epic("Epic3", "desc");

    @BeforeAll
    public static void createTaskManager() {
        taskManager = Managers.getDefault();
    }

    @Test
    void epicShouldBeCreate() {
        Epic epic = new Epic("Epic", "desc");
        taskManager.createEpic(epic);
        assertEquals(epic, taskManager.getEpic(epic.getId()));
    }

    @Test
    void oneEpicShouldBeDeleted() {
        taskManager.createEpic(epic1);
        taskManager.deleteEpic(epic1.getId());
        assertNull(taskManager.getEpic(epic1.getId()));
    }

    @Test
    void allEpicsShouldBeDeleted() {
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createEpic(epic3);
        taskManager.deleteEpics();

        assertTrue(taskManager.getEpics().isEmpty());
    }

    @Test
    void epicShouldBeUpdate() {
        Epic epic = new Epic("Epic", "desc");
        taskManager.createEpic(epic);

        Epic updateEpic = new Epic("EpicUpdate", "descUpdate", Status.DONE);
        taskManager.updateEpic(epic.getId(), updateEpic);
        assertEquals(updateEpic, taskManager.getEpic(epic.getId()), "Эпик не изменился ");
    }

    @Test
    void epicStatusShouldBeInProgress () {
        Epic epic = new Epic("Epic", "desc");
        SubTask subTask1 = new SubTask("SubTask1", "desc", Status.NEW);
        SubTask subTask2 = new SubTask("SubTask2", "desc", Status.NEW);
        SubTask subTask3 = new SubTask("SubTask3", "desc", Status.IN_PROGRESS);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.addSubtaskInEpic(epic, subTask1);
        taskManager.addSubtaskInEpic(epic, subTask2);
        taskManager.addSubtaskInEpic(epic, subTask3);

        assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    void epicStatusShouldBeDone () {
        Epic epic = new Epic("Epic", "desc");
        SubTask subTask1 = new SubTask("SubTask1", "desc", Status.DONE);
        SubTask subTask2 = new SubTask("SubTask2", "desc", Status.DONE);
        SubTask subTask3 = new SubTask("SubTask3", "desc", Status.DONE);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.addSubtaskInEpic(epic, subTask1);
        taskManager.addSubtaskInEpic(epic, subTask2);
        taskManager.addSubtaskInEpic(epic, subTask3);

        assertEquals(epic.getStatus(), Status.DONE);
    }
}
