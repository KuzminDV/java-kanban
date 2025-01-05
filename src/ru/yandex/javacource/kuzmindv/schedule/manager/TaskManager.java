package ru.yandex.javacource.kuzmindv.schedule.manager;

import ru.yandex.javacource.kuzmindv.schedule.task.Epic;
import ru.yandex.javacource.kuzmindv.schedule.task.Subtask;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;

import java.util.ArrayList;

public interface TaskManager {
    // получение списка всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    ArrayList<Task> getTasks();

    // удаление всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    void deleteAllTasks();

    // получение задачи ru.yandex.javacource.kuzmindv.schedule.task.Task по id
    Task getTaskById(Integer id);

    // добавление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
    Task createTask(Task task);

    // обновление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
    void updateTask(Task task);

    // удаление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task из HashMap idToTask
    Task deleteTask(Integer id);

    // создание нового ru.yandex.javacource.kuzmindv.schedule.task.Epic
    Epic createEpic(Epic epic);

    // Поиск ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
    Epic getEpicById(Integer id);

    // Создание нового ru.yandex.javacource.kuzmindv.schedule.task.Subtask
    Subtask createSubtask(Subtask subtask);

    ArrayList<Task> getEpics();

    ArrayList<Task> getSubtasks();

    // Возврат всех ru.yandex.javacource.kuzmindv.schedule.task.Subtask определенного ru.yandex.javacource.kuzmindv.schedule.task.Epic
    ArrayList<Subtask> getSubtasksForEpic(Integer epicId);

    // Обновление ru.yandex.javacource.kuzmindv.schedule.task.Epic
    void updateEpic(Epic epic);

    // Обновление ru.yandex.javacource.kuzmindv.schedule.task.Subtask
    void updateSubtask(Subtask subtask);

    // Возврат ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
    Subtask getSubtaskById(Integer id);

    // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
    void removeEpicById(Integer id);

    // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
    void removeSubtaskById(Integer id);

    // Удаление всех Epics
    void removeAllEpics();

    // Удаление всех Subtasks
    void removeAllSubtasks();
}
