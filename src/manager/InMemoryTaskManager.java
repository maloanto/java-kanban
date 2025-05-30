package manager;


import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
public class InMemoryTaskManager implements TaskManager{
    private int id = 1;

    private final HashMap<Integer, Task> taskMap = new HashMap<>();
    private final HashMap<Integer, SubTask> subTaskMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicMap = new HashMap<>();
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int createTask(Task task) {
        task.setId(getNextId());
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int createSubTask(SubTask subTask) {
        subTask.setId(getNextId());
        subTaskMap.put(subTask.getId(), subTask);
        return subTask.getId();
    }

    @Override
    public int createEpic(Epic epic) {
        epic.setId(getNextId());
        epicMap.put(epic.getId(), epic);
        updateEpicStatus(epic);
        return epic.getId();
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTaskMap.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public void deleteTasks() {
        taskMap.clear();
    }

    @Override
    public void deleteSubTasks() {
        subTaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.getSubTaskId().clear();
        }
    }

    @Override
    public void deleteEpics() {
        epicMap.clear();
        subTaskMap.clear();
    }

    @Override
    public Task getTask(int id) {
        Task task = taskMap.getOrDefault(id, null);
        if (task != null)
            historyManager.add(taskMap.get(id));
        return task;
    }

    @Override
    public SubTask getSubTask(int subTaskId) {
        SubTask subTask = subTaskMap.getOrDefault(subTaskId, null);
        if (subTask != null)
            historyManager.add(subTaskMap.get(subTaskId));
        return subTask;
    }

    @Override
    public Epic getEpic(int epicId) {
        Epic epic = epicMap.getOrDefault(epicId, null);
        if (epic != null)
            historyManager.add(epicMap.get(epicId));
        return epic;
    }

    @Override
    public void updateTask(int id, Task task) {
        task.setId(id);
        taskMap.put(id, task);
    }

    @Override
    public void addSubtaskInEpic(Epic epic, SubTask subTask) {
        subTask.setEpicId(epic.getId());
        epic.getSubTaskId().add(subTask.getId());
        updateEpicStatus(epic);
    }

    @Override
    public void updateSubTask(int id, SubTask subTask) {
        int epicId = subTaskMap.get(id).getEpicId();
        subTask.setId(id);
        subTask.setEpicId(epicId);
        subTaskMap.put(id, subTask);
        Epic epic = epicMap.get(subTask.getEpicId());
        updateEpicStatus(epic);
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        epic.setId(id);
        epicMap.put(id, epic);
    }

    @Override
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    @Override
    public void deleteSubTask(int id) {
        Epic epic = epicMap.get(subTaskMap.get(id).getEpicId());
        subTaskMap.remove(id);
        epic.getSubTaskId().remove(Integer.valueOf(id));
        updateEpicStatus(epic);
    }

    @Override
    public void deleteEpic(int id) {
        Epic epicToDelete = epicMap.get(id);
        for (Integer subTaskId : epicToDelete.getSubTaskId()) {
            subTaskMap.remove(subTaskId);
        }
        epicMap.remove(id);
    }

    @Override
    public ArrayList<SubTask> getAllSubTaskByEpic(Epic epic) {
        ArrayList<SubTask> resultList = new ArrayList<>();

        for (Integer subtaskId : epic.getSubTaskId()) {
            if (subTaskMap.containsKey(subtaskId)) {
                resultList.add(subTaskMap.get(subtaskId));
            }
        }
        return resultList;
    }

    private void updateEpicStatus(Epic epic) {

        int countSubTask = epic.getSubTaskId().size();
        if (countSubTask == 0) {
            epic.setStatus(Status.NEW);
            return;
        }

        int counterDone = 0;
        int counterInProgress = 0;

        for (Integer subTaskId : epic.getSubTaskId()) {
            if (subTaskMap.get(subTaskId).getStatus().equals(Status.DONE)) {
                counterDone++;
            } else if (subTaskMap.get(subTaskId).getStatus().equals(Status.IN_PROGRESS)) {
                counterInProgress++;
            }
        }

        if (counterDone == countSubTask) {
            epic.setStatus(Status.DONE);
        } else if (counterInProgress > 0 || counterDone > 0) {
            epic.setStatus(Status.IN_PROGRESS);
        } else {
            epic.setStatus(Status.NEW);
        }
    }

    private int getNextId() {
        return id++;
    }
}
