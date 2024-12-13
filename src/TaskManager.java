import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {
    public HashMap<Integer, Task> idToTask = new HashMap<>();
    public HashMap<Integer, Epic> idToEpic = new HashMap<>();
    public HashMap<Integer, Subtask> idToSubtask = new HashMap<>();
    private Integer counter = 0;

    // получение списка всех задач Task
    public String findAllTasks() {
        return idToTask.toString();
    }

    // удаление всех задач Task
    public void deleteAllTasks() {
        idToTask.clear();
    }

    // получение задачи Task по id
    public Task findTasksById(Integer id) {
        Task task = idToTask.get(id);
        if (task == null) {
            return null;
        }
        return task;
    }

    // добавление задачи Task в HashMap idToTask
    public Task createTask(Task task) {
        counter++;
        task.setId(counter);
        idToTask.put(counter, task);
        return task;
    }

    // обновление задачи Task в HashMap idToTask
    public Task updateTask(Task task) {
        if (idToTask.containsKey(task.getId())) {
            Task t = idToTask.get(task.getId());
            t.setName(task.getName());
            t.setDescription(task.getDescription());
            t.setStatus(task.getStatus());
            idToTask.put(task.getId(), t);
            return task;
        } else {
            return null;
        }
    }

    // удаление задачи Task из HashMap idToTask
    public Task deleteTask(Integer id) {
        Task t = idToTask.remove(id);
        return t;
    }

    // создание нового Epic
    public Epic createEpic(Epic epic) {
        counter++;
        epic.setId(counter);
        idToEpic.put(counter, epic);
        return epic;
    }

    // Поиск Epic по id
    public Epic findEpicById(Integer id) {
        Epic epic = idToEpic.get(id);
        if (epic == null) {
            return null;
        }
        return epic;
    }

    // Создание нового Subtask
    public Subtask createSubtask(Subtask subtask, Epic epic) {
        counter++;
        subtask.setId(counter);
        idToSubtask.put(counter, subtask);
        epic.getSubtasks().add(subtask);
        return subtask;
    }

    // Печать всех Epic
    public String findAllEpics() {
        return idToEpic.toString();
    }

    // Печать всех Subtask
    public String findAllSubtasks() {
        return idToSubtask.toString();
    }

    // Возврат всех Subtask определенного Epic
    public ArrayList<Subtask> getSubtasksForEpic(Epic epic) {
        epic = idToEpic.get(epic.getId());
        if (epic != null) {
            ArrayList<Subtask> subtasks = new ArrayList<>();
            subtasks.addAll(epic.getSubtasks());
            return subtasks;
        }
        return null;
    }

    // Обновление Epic
    public Epic updateEpic(Epic oldEpic, Epic newEpic) {
        if (idToEpic.containsKey(oldEpic.getId())) {
            Epic existingEpic = idToEpic.get(oldEpic.getId());
            existingEpic.setName(newEpic.getName());
            existingEpic.setDescription(newEpic.getDescription());
            existingEpic.setStatus(calculateEpicStatus(existingEpic));
            return existingEpic;
        }
        return null;
    }

    // Обновление Subtask
    public Subtask updateSubtask(Subtask oldSubtask, Subtask newSubtask) {
        if (idToSubtask.containsKey(oldSubtask.getId())) {
            Subtask existingSubtask = idToSubtask.get(oldSubtask.getId());
            existingSubtask.setName(newSubtask.getName());
            existingSubtask.setDescription(newSubtask.getDescription());
            existingSubtask.setStatus(newSubtask.getStatus());
            Epic epic = findEpicForSubtask(existingSubtask);
            if (epic != null) {
                epic.setStatus(calculateEpicStatus(epic));
            }
            return existingSubtask;
        }
        return null;
    }

    // Определение статуса Epic
    private Status calculateEpicStatus(Epic epic) {
        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getStatus().equals(Status.NEW) || subtask.getStatus().equals(Status.IN_PROGRESS)) {
                return Status.IN_PROGRESS;
            }
        }
        return Status.DONE;
    }

    // Поиск Epic по Subtask
    public Epic findEpicForSubtask(Subtask subtask) {
        for (Epic epic : idToEpic.values()) {
            if (epic.getSubtasks().contains(subtask)) {
                return epic;
            }
        }
        return null;
    }

    // Возврат Subtask по id
    public Subtask getSubtaskForEpicId(Epic epic, Integer subtaskId) {
        if (idToEpic.containsKey(epic.getId())) {
            for (Subtask subtask : idToEpic.get(epic.getId()).getSubtasks()) {
                if (subtask.getId() == subtaskId) {
                    return subtask;
                }
            }
        }
        return null;
    }

    // Удаление Subtask из Epic
    public void removeSubtaskFromEpic(Epic epic, Subtask subtask) {
        if (idToEpic.containsKey(epic.getId())) {
            for (Subtask tempSubtask : idToEpic.get(epic.getId()).getSubtasks()) {
                if (tempSubtask.equals(subtask)) {
                    epic.getSubtasks().remove(subtask);
                    calculateEpicStatus(epic);
                }
                return;
            }
        }
    }

    // Удаление Epic
    public void removeEpic(Epic epic) {
        if (idToEpic.containsKey(epic.getId())) {
            idToEpic.remove(epic.getId());
        }
    }

    // Удаление Epic по id
    public void removeEpicById(Integer id) {
        if (idToEpic.containsKey(id)) {
            idToEpic.remove(id);
        }
    }

    // Удаление Subtask по id
    public void removeSubtaskById(Integer id) {
        Subtask tempSubtask = idToSubtask.get(id);
        Epic tempEpic = findEpicForSubtask(tempSubtask);
        for (Subtask subtask : tempEpic.getSubtasks()) {
            if (subtask.equals(tempSubtask)) {
                tempEpic.getSubtasks().remove(subtask);
                calculateEpicStatus(tempEpic);
                return;
            }
        }
    }

    // Удаление всех Task, Epic и Subtask
    public void removeAll() {
        idToTask.clear();
        idToEpic.clear();
        idToSubtask.clear();
    }

}
