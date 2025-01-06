package ru.yandex.javacource.kuzmindv.schedule.manager;

import ru.yandex.javacource.kuzmindv.schedule.task.Epic;
import ru.yandex.javacource.kuzmindv.schedule.task.Status;
import ru.yandex.javacource.kuzmindv.schedule.task.Subtask;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private Integer counter = 0;
    private final HistoryManager historyManager;


    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    // получение списка всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // удаление всех задач ru.yandex.javacource.kuzmindv.schedule.task.Task
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    // получение задачи ru.yandex.javacource.kuzmindv.schedule.task.Task по id
    @Override
    public Task getTaskById(Integer id) {
        final Task task = tasks.get(id);
        historyManager.add(task);
        return task;
        // Михаил, привет. Я исправил согласно твоего замечания, но идея была именно в том, чтобы в истории задача
        // сохранялась именно в той редакции, в которой она была туда добавлена. Об этом говорил Олег в рамках Q&A.
    }

        // добавление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
        @Override
        public Task createTask (Task task) {
            counter++;
            task.setId(counter);
            tasks.put(counter, task);
            return task;
        }

        // обновление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task в HashMap idToTask
        @Override
        public void updateTask (Task task) {
            int id = task.getId();
            Task savedTask = tasks.get(id);
            if (savedTask == null) {
                return;
            }
            tasks.put(id, task);
        }

        // удаление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task из HashMap idToTask
        @Override
        public Task deleteTask (Integer id) {
            Task t = tasks.remove(id);
            return t;
        }

        // создание нового ru.yandex.javacource.kuzmindv.schedule.task.Epic
        @Override
        public Epic createEpic (Epic epic) {
            counter++;
            epic.setId(counter);
            epics.put(counter, epic);
            return epic;
        }

        // Поиск ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
        @Override
        public Epic getEpicById (Integer id) {
            final Epic epic = epics.get(id);
            if (epic == null) {
                return null;
            }
            historyManager.add(epic);
            return epic;
        }

        // Создание нового ru.yandex.javacource.kuzmindv.schedule.task.Subtask
        public Subtask createSubtask (Subtask subtask) {
            counter++;
            Integer epicId = subtask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
            subtask.setId(counter);
            subtasks.put(counter, subtask);
            epic.setSubtaskIds(subtask.getId());
            calculateEpicStatus(epicId);
            return subtask;
        }

        @Override
        public List<Task> getEpics () {
            return new ArrayList<>(epics.values());
        }

        @Override
        public List<Task> getSubtasks () {
            return new ArrayList<>(subtasks.values());
        }

        // Возврат всех ru.yandex.javacource.kuzmindv.schedule.task.Subtask определенного ru.yandex.javacource.kuzmindv.schedule.task.Epic
        @Override
        public ArrayList<Subtask> getSubtasksForEpic (Integer epicId) {
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
        @Override
        public void updateEpic (Epic epic) {
            final Epic savedEpic = epics.get(epic.getId());
            if (savedEpic == null) {
                return;
            }
            epic.setSubtasksIds(savedEpic.getSubtasks());
            epic.setStatus(savedEpic.getStatus());
            epics.put(epic.getId(), epic);
        }

        // Обновление ru.yandex.javacource.kuzmindv.schedule.task.Subtask
        @Override
        public void updateSubtask (Subtask subtask) {
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

        // Возврат ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
        @Override
        public Subtask getSubtaskById (Integer id) {
            final Subtask subtask = subtasks.get(id);
            historyManager.add(subtask);
            return subtask;
        }

        // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Epic по id
        @Override
        public void removeEpicById (Integer id) {
            Epic epic = epics.remove(id);
            if (epic == null) {
                return;
            }
            for (Integer subtaskId : epic.getSubtasks()) {
                subtasks.remove(subtaskId);
            }
        }

        // Удаление ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id
        @Override
        public void removeSubtaskById (Integer id) {
            Subtask subtask = subtasks.get(id);
            if (subtask == null) {
                return;
            }
            Epic epic = epics.get(subtask.epicId);
            epic.getSubtasks().remove(id);
            calculateEpicStatus(epic.getId());
        }

        // Удаление всех Epics
        @Override
        public void removeAllEpics () {
            epics.clear();
            subtasks.clear();
        }

        // Удаление всех Subtasks
        @Override
        public void removeAllSubtasks () {
            for (Epic epic : epics.values()) {
                epic.getSubtasks().clear();
                calculateEpicStatus(epic.getId());
            }
            subtasks.clear();
        }

    // Определение статуса ru.yandex.javacource.kuzmindv.schedule.task.Epic
    private void calculateEpicStatus (Integer epicId) {
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
    }
