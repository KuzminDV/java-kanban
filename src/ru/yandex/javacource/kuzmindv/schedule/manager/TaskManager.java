package ru.yandex.javacource.kuzmindv.schedule.manager;

import ru.yandex.javacource.kuzmindv.schedule.task.Epic;
import ru.yandex.javacource.kuzmindv.schedule.task.Status;
import ru.yandex.javacource.kuzmindv.schedule.task.Subtask;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;

import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {
    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private Integer counter = 0;

    // получение списка всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // удаление всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    public void deleteAllTasks() {
        tasks.clear();
    }

    // получение задачи ru.yandex.javacource.kuzmindv.schedule.task.Task по id
    public Task getTask(int id) {
        return tasks.get(id);
    }

    // добавление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
    public Task createTask(Task task) {
        counter++;
        task.setId(counter);
        tasks.put(counter, task);
        return task;
    }

    // обновление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
    public void updateTask(Task task) {
        int id = task.getId();
        Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
    }

    // удаление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task из HashMap idToTask
    public Task deleteTask(Integer id) {
        Task t = tasks.remove(id);
        return t;
    }

    // создание нового ru.yandex.javacource.kuzmindv.schedule.task.Epic
    public Epic createEpic(Epic epic) {
        counter++;
        epic.setId(counter);
        epics.put(counter, epic);
        return epic;
    }

    // Поиск ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
    public Epic findEpicById(Integer id) {
        Epic epic = epics.get(id);
        if (epic == null) {
            return null;
        }
        return epic;
    }

    // Создание нового ru.yandex.javacource.kuzmindv.schedule.task.Subtask
    public Subtask createSubtask(Subtask subtask) {
        counter++;
        Integer epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        subtask.setEpicId(epicId);
        subtask.setId(counter);
        subtasks.put(counter, subtask);
        epic.setSubtasksIds(subtask.getId());
        calculateEpicStatus(epicId);
        return subtask;
    }

    public ArrayList<Task> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Task> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Возврат всех ru.yandex.javacource.kuzmindv.schedule.task.Subtask определенного ru.yandex.javacource.kuzmindv.schedule.task.Epic
    public ArrayList<Subtask> getSubtasksForEpic(Integer epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Subtask> subtasksID = new ArrayList<>();
        for (Integer id : epic.getSubtasks()) {
            if (epic.getSubtasks().contains(id)) {
                subtasksID.add(subtasks.get(id));
            }
        }
        return subtasksID;
    }

    // Обновление ru.yandex.javacource.kuzmindv.schedule.task.Epic
    public void updateEpic(Epic epic) {
        Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());
    }

    // Обновление ru.yandex.javacource.kuzmindv.schedule.task.Subtask
    public void updateSubtask(Subtask subtask) {
        Subtask savedSubtask = subtasks.get(subtask.getId());
        if (savedSubtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return;
        }
        subtasks.put(subtask.getId(), savedSubtask);
        calculateEpicStatus(subtask.getEpicId());
    }


    // Определение статуса ru.yandex.javacource.kuzmindv.schedule.task.Epic
    private void calculateEpicStatus(Integer epicId) {
        if (epics.containsKey(epicId)) {
            Epic epic = epics.get(epicId);
            boolean isDone = true;
            boolean isNew = true;
            for (Integer subtaskId : epic.getSubtasks()) {
                if (!subtasks.get(subtaskId).getStatus().equals(Status.DONE)) {
                    isDone = false;
                }
                if (!subtasks.get(subtaskId).getStatus().equals(Status.IN_PROGRESS)) {
                    isNew = false;
                }
            }
            if (isDone == true && isNew == false) {
                epic.setStatus(Status.DONE);
            } else if (isDone == false && isNew == true) {
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    // Возврат ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
    public Subtask getSubtask(Integer id) {
       return subtasks.get(id);
    }

    // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
    public void removeEpicById(Integer id) {
        Epic epic = epics.remove(id);
        if (epic == null) {
            return;
        }
        for (Integer subtaskId : epic.getSubtasks()) {
            subtasks.remove(subtaskId);
        }
    }

    // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
    public void removeSubtaskById(Integer id) {
        Subtask subtask = subtasks.get(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.epicId);
        epic.getSubtasks().remove(id);
        calculateEpicStatus(epic.getId());
    }

    // Удаление всех Epics
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Удаление всех Subtasks
    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            calculateEpicStatus(epic.getId());
        }
        subtasks.clear();
    }
}
