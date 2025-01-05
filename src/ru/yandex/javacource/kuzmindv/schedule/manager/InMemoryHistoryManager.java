package ru.yandex.javacource.kuzmindv.schedule.manager;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private ArrayList<Task> tasksHistory = new ArrayList<>();

    @Override
    public void addTaskToHistory(Task task) {
        if(tasksHistory.size() < 10){
            tasksHistory.add(task);
        } else {
            tasksHistory.removeFirst();
            tasksHistory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(tasksHistory);
    }
}
