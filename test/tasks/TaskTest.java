package tasks;


import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    static TaskManager taskManager;
    Task task1 = new Task("Task1", "desc", Status.NEW);
    Task task2 = new Task("Task2", "desc", Status.NEW);
    Task task3 = new Task("Task3", "desc", Status.NEW);

    @BeforeAll
    public static void createTaskManager() {
        taskManager = Managers.getDefault();
    }

    @Test
    void taskShouldBeEquals() {
        Task task1 = new Task("task", "desk", Status.NEW);
        task1.setId(1);

        Task task2 = new Task("task", "desk", Status.NEW);
        task2.setId(1);

        assertEquals(task1, task2);
    }

    @Test
    void taskShouldBeNotEquals() {
        Task task1 = new Task("task", "desk", Status.NEW);
        task1.setId(1);

        Task task2 = new Task("task", "desk", Status.NEW);
        task2.setId(2);

        assertNotEquals(task1, task2);
    }

    @Test
    void taskShouldBeCreate() {
        Task task = new Task("Task", "desc");
        taskManager.createTask(task);
        assertEquals(task, taskManager.getTask(task.getId()));
    }

    @Test
    void taskShouldBeUpdate() {
        Task task = new Task("Task", "desc", Status.NEW);
        taskManager.createTask(task);
        Task updateTask = new Task("TaskUpdate", "descUpdate", Status.IN_PROGRESS);
        taskManager.updateTask(task.getId(), updateTask);

        assertEquals(updateTask, taskManager.getTask(task.getId()), "Задача не изменилась");
    }

    @Test
    void oneTaskShouldBeDeleted() {
        taskManager.createTask(task1);
        taskManager.deleteTask(task1.getId());
        assertNull(taskManager.getTask(task1.getId()));
    }

    @Test
    void allTasksShouldBeDeleted() {
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);
        taskManager.deleteTasks();

        assertTrue(taskManager.getTasks().isEmpty());
    }
}
