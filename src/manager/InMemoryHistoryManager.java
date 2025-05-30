package manager;


import tasks.Task;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager{
    public final LinkedList<Task> history = new LinkedList<>();
    private final static int SIZE_HISTORY_LIST = 10;

    @Override
    public void add(Task task) {
        if (history.size() == SIZE_HISTORY_LIST) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public LinkedList<Task> getHistory() {
        return history;
    }

    @Override
    public void deleteHistory() {
        history.clear();
    }
}
