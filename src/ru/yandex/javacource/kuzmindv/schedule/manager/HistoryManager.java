package ru.yandex.javacource.kuzmindv.schedule.manager;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;
import java.util.List;

public interface HistoryManager {

    void addTaskToHistory(Task task);

    List<Task> getHistory();
}