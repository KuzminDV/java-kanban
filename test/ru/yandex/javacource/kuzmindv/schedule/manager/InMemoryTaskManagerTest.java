package ru.yandex.javacource.kuzmindv.schedule.manager;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.kuzmindv.schedule.task.Epic;
import ru.yandex.javacource.kuzmindv.schedule.task.Status;
import ru.yandex.javacource.kuzmindv.schedule.task.Subtask;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    HistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);

    @Test
    void areTasksEqualIfTheirIdIsEqual() {
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        Task actuallTask = taskManager.getTaskById(createdTask.getId());
        assertNotNull(actuallTask.getId());
        assertEquals(createdTask.getName(), actuallTask.getName());
        assertEquals(createdTask.getDescription(), actuallTask.getDescription());
        assertEquals(createdTask.getStatus(), actuallTask.getStatus());
    }

    @Test
    void areEpicsEqualIfTheirIdIsEqual() {
        Epic createdEpic = new Epic(null, "Epic1", "Epic1ForTest", null);
        taskManager.createEpic(createdEpic);
        Epic actuallEpic = taskManager.getEpicById(createdEpic.getId());
        assertNotNull(actuallEpic.getId());
        assertEquals(createdEpic.getName(), actuallEpic.getName());
        assertEquals(createdEpic.getDescription(), actuallEpic.getDescription());
        assertEquals(createdEpic.getStatus(), actuallEpic.getStatus());
    }

    @Test
    void areSubtasksEqualIfTheirIdIsEqual() {
        Epic createdEpic = new Epic(null, "Epic", "EpicForTestSubtasks", null);
        taskManager.createEpic(createdEpic);
        Subtask createdSubtask = new Subtask(null, "Subtask1", "Subtask1ForTest", Status.NEW,
                createdEpic.getId());
        taskManager.createSubtask(createdSubtask);
        Subtask actuallSubtask = taskManager.getSubtaskById(createdSubtask.getId());
        assertNotNull(actuallSubtask.getId());
        assertEquals(createdSubtask.getName(), actuallSubtask.getName());
        assertEquals(createdSubtask.getDescription(), actuallSubtask.getDescription());
        assertEquals(createdSubtask.getStatus(), actuallSubtask.getStatus());
    }

    @Test
    void utilityClassAlwaysReturnsInitializedAndReadyManagers () {
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        Epic createdEpic = new Epic(null, "Epic", "EpicForTestSubtasks", null);
        taskManager.createEpic(createdEpic);
        Subtask createdSubtask = new Subtask(null, "Subtask1", "Subtask1ForTest", Status.NEW,
                createdEpic.getId());
        taskManager.createSubtask(createdSubtask);
        Subtask actuallSubtask = taskManager.getSubtaskById(createdSubtask.getId());
        assertNotNull(taskManager);
        //Проверка inMemoryTaskManager добавления задач разного типа
        assertEquals(1, taskManager.getTasks().size());
        assertEquals(1, taskManager.getEpics().size());
        assertEquals(1, taskManager.getSubtasks().size());
        // Поиск inMemoryTaskManager разных задач по id
        assertEquals(createdTask.getId(), taskManager.getTaskById(createdTask.getId()).getId());
        assertEquals(createdEpic.getId(), taskManager.getEpicById(createdEpic.getId()).getId());
        assertEquals(createdSubtask.getId(), taskManager.getSubtaskById(createdSubtask.getId()).getId());
    }

    @Test
    void testHistoryManagerTaskFromHistoryNotUpdate() {
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        taskManager.getTaskById(createdTask.getId());
        createdTask.setStatus(Status.DONE);
        assertNotEquals(createdTask.getStatus(), taskManager.getHistoryFromHistoryManager().getFirst().getStatus());
    }

    @Test
    void tasksWithTheSpecifiedAndGeneratedIdDoNotConflict() {
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        createdTask.setId(777);
        Task createdTask2 = new Task(null, "Task2", "Task2ForTest", Status.NEW);
        taskManager.createTask(createdTask2);
        assertNotEquals(createdTask.getId(), createdTask2.getId());
        System.out.println(createdTask.getId());
        System.out.println(createdTask2.getId());
    }

    @Test
    void addTaskToHistoryManager () {
        System.out.println(historyManager.getHistory().size());
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        taskManager.getTaskById(createdTask.getId());
        assertNotNull(historyManager.getHistory(), "История не пустая.");
        assertEquals(1, historyManager.getHistory().size(), "История не пустая.");
        System.out.println(historyManager.getHistory().size());
    }

    @Test
    void getSubtaskByIdTest() {
        Epic createdEpic = new Epic(null, "Epic", "EpicForTestSubtasks", null);
        taskManager.createEpic(createdEpic);
        Subtask createdSubtask = new Subtask(null, "Subtask1", "Subtask1ForTest", Status.NEW,
                createdEpic.getId());
        taskManager.createSubtask(createdSubtask);
        Subtask actuallSubtask = taskManager.getSubtaskById(createdSubtask.getId());
        assertNotNull(actuallSubtask.getId());
        assertEquals(createdSubtask, actuallSubtask);
    }

    @Test
    void taskNotChangeAfterAddToManager () {
        Task createdTask = new Task(null, "Task1", "Task1ForTest", Status.NEW);
        taskManager.createTask(createdTask);
        Task actuallTask = taskManager.getTaskById(createdTask.getId());
        assertNotNull(actuallTask.getId());
        assertEquals(createdTask.getName(), actuallTask.getName());
        assertEquals(createdTask.getDescription(), actuallTask.getDescription());
        assertEquals(createdTask.getStatus(), actuallTask.getStatus());
    }

    @Test
    void getSubtaskByEpicTest() {
        Epic createdEpic = new Epic(null, "Epic", "EpicForTestSubtasks", null);
        taskManager.createEpic(createdEpic);
        Subtask createdSubtask = new Subtask(null, "Subtask1", "Subtask1ForTest", Status.NEW,
                createdEpic.getId());
        taskManager.createSubtask(createdSubtask);
        ArrayList<Subtask> actuallSubtasks = taskManager.getSubtasksForEpic(createdEpic.getId());
        assertNotNull(actuallSubtasks);
        System.out.println(actuallSubtasks.size());
    }

    @Test
    void testManager() {
        TaskManager taskManagerTest = Managers.getDefault();
        taskManagerTest.createTask(new Task(null, "Task1", "Task1ForTest", Status.NEW));
        Epic epic = new Epic(null, "Epic", "EpicForTestSubtasks", null);
        taskManagerTest.createTask(epic);
        taskManagerTest.createTask(new Subtask(null, "Subtask1", "Subtask1ForTest", Status.NEW,
                epic.getId()));
    }


}