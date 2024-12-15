import ru.yandex.javacource.kuzmindv.schedule.manager.TaskManager;
import ru.yandex.javacource.kuzmindv.schedule.task.Epic;
import ru.yandex.javacource.kuzmindv.schedule.task.Status;
import ru.yandex.javacource.kuzmindv.schedule.task.Subtask;
import ru.yandex.javacource.kuzmindv.schedule.task.Task;

public class Main {

    public static void main(String[] args) {

        Task task1 = new Task(null, "Задача ТАСК1", "Описание задачи ТАСК1", Status.NEW);
        Task task2 = new Task(null, "Задача ТАСК2", "Описание задачи ТАСК2", Status.NEW);
        Task task3 = new Task(null, "Задача ТАСК3", "Описание задачи ТАСК3", Status.NEW);
        Task task4 = new Task(null, "Задача ТАСК4", "Описание задачи ТАСК4", Status.NEW);
        Task taskNew3 = new Task(3, "Обновление задачи 3!!!", "Новое описание задачи 3!!!", Status.DONE);
        Task taskNew4 = new Task(4, "Обновление задачи 4!!!", "Новое описание задачи 4!!!", Status.IN_PROGRESS);
        Epic epic1 = new Epic(null, "Задача ЕПИК1", "Описание задачи ЕПИК1",  Status.NEW);
        Subtask subtask11 = new Subtask(null, "Задача СУБТАСК1", "Описание задачи СУБТАСК1", Status.NEW, epic1.getId());
        Subtask subtask12 = new Subtask(null, "Задача СУБТАСК2", "Описание задачи СУБТАСК2", Status.NEW, epic1.getId());
        Subtask subtask13 = new Subtask(null, "Задача СУБТАСК3", "Описание задачи СУБТАСК3", Status.NEW, epic1.getId());
        Epic epic2 = new Epic(null, "Задача ЕПИК2", "Описание задачи ЕПИК2", Status.NEW);
        Subtask subtask21 = new Subtask(null, "Задача СУБТАСК21", "Описание задачи СУБТАСК21", Status.NEW, epic2.getId());
        Subtask subtask22 = new Subtask(null, "Задача СУБТАСК22", "Описание задачи СУБТАСК22", Status.NEW, epic2.getId());
        Subtask subtask23 = new Subtask(null, "Задача СУБТАСК23", "Описание задачи СУБТАСК23", Status.NEW, epic2.getId());
        Subtask subtask25 = new Subtask(null, "НОВАЯ Задача СУБТАСК25", "НОВОЕ Описание задачи СУБТАСК25", Status.NEW, epic2.getId());
        Epic epic2New = new Epic(null, "Обновленный ru.yandex.javacource.kuzmindv.schedule.task.Epic 2" , "Обновленное описание  ЕПИК2", Status.NEW);


        TaskManager taskManager = new TaskManager();

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());
        System.out.println("Получение задача по id 3 ✅");
        System.out.println(taskManager.getTask(3));

        System.out.println("Добавление новой задачи ✅");
        taskManager.createTask(task4);
        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());

        System.out.println("Обновление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task 3 ✅");
        taskManager.updateTask(taskNew3);
        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());

        System.out.println("Обновление задачи ru.yandex.javacource.kuzmindv.schedule.task.Task 4 ✅");
        taskManager.updateTask(taskNew4);
        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());

        System.out.println("Удаление задачи 2 по id ✅");
        taskManager.deleteTask(2);
        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());

        System.out.println("Удаление всех задач ✅");
        taskManager.deleteAllTasks();
        System.out.println("Получение списка всех задач TASK: ✅");
        System.out.println(taskManager.getTasks().toString());

        System.out.println("Добавление нового Epic1 и его Subtasks");
        taskManager.createEpic(epic1);
        taskManager.createSubtask(subtask11, epic1);
        taskManager.createSubtask(subtask12, epic1);
        taskManager.createSubtask(subtask13, epic1);
        System.out.println("Печать всех эпиков после добавления ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println("Печать всех Subtasks ✅");
        System.out.println(taskManager.findAllSubtasks());

        System.out.println("Добавление нового Epic2 и его Subtasks");
        taskManager.createEpic(epic2);
        taskManager.createSubtask(subtask21, epic2);
        taskManager.createSubtask(subtask22, epic2);
        taskManager.createSubtask(subtask23, epic2);
        System.out.println("Печать всех эпиков после добавления ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println("Печать всех Subtasks ✅");
        System.out.println(taskManager.findAllSubtasks());

        System.out.println("Печать всех Subtasks по Epic1 ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic1.getId())) { System.out.println(subtask.toString());}

        System.out.println("Печать всех Subtasks по Epic2 ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) { System.out.println(subtask.toString());}

        Subtask subtask22New = new Subtask(subtask22.getId(), "НОВАЯ Задача СУБТАСК24", "НОВОЕ Описание задачи СУБТАСК24", Status.DONE, epic2.getId());

        System.out.println("Изменение Subtasks 22 ✅");
        taskManager.updateSubtask(subtask22New);
        System.out.println("Получение списка всех Subtasks Epic2: ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) { System.out.println(subtask.toString());}

        System.out.println("Печать всех Эпиков  ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println("Обновление ru.yandex.javacource.kuzmindv.schedule.task.Epic 2 ✅");
        taskManager.updateEpic(epic2New);
        System.out.println("Получение списка всех Epics: ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println(epic2.getSubtasks());
        System.out.println(taskManager.subtasks.values());

        System.out.println("Изменение subtask22 ");
        taskManager.updateSubtask(subtask22New);
        System.out.println("Получение списка всех Subtasks Epic2: ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) { System.out.println(subtask.toString());}


        System.out.println("Получение ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id (Subtask21 из Epic2) ✅");
        System.out.println(taskManager.getSubtask(subtask21.getId()));

        System.out.println("Получение ru.yandex.javacource.kuzmindv.schedule.task.Subtask по id (Subtask11 из Epic1) ✅");
        System.out.println(taskManager.getSubtask(subtask11.getId()));


        System.out.println("Поиск ru.yandex.javacource.kuzmindv.schedule.task.Epic  по id ✅");
        System.out.println(taskManager.findEpicById(epic1.getId()));

        System.out.println("Удаление Subtask11 из Epic1 ✅");
        taskManager.removeSubtaskFromEpic(subtask11.getId());
        System.out.println("Получение списка всех Subtasks Epic1: ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic1.getId())) {
            if(subtask == null){
                continue;
            }
            System.out.println(subtask.toString());
        }

        System.out.println("Текущий статус TASK1: " + task1.getStatus() + "✅");
        System.out.println(task1.toString());
        System.out.println("Изменение статус TASK1 с" + task1.getStatus() + "на DONE ✅");
        task1.setStatus(Status.DONE);
        System.out.println("Измененный статус TASK1:" + task1.getStatus() + "✅");
        System.out.println(task1.toString());

        System.out.println("Текущий статус SUBTASK21: " + subtask21.getStatus() + "✅");
        System.out.println("Изменение статуса SUBTASK11 (Epic2) с " + subtask21.getStatus() + " на DONE");
        subtask11.setStatus(Status.DONE);
        System.out.println("Измененный статус SUBTASK11:" + subtask11.getStatus() + "✅");

        System.out.println("Изменение остальных статусов Subtasks Epic2 ✅");
        subtask21.setStatus(Status.DONE);
        subtask22.setStatus(Status.IN_PROGRESS);
        subtask23.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask22New);
        subtask22.setStatus(Status.NEW);
        Subtask subtask22NewStatus = new Subtask(subtask22New.getId(), "НОВАЯ Задача СУБТАСК24", "НОВОЕ Описание задачи СУБТАСК24", Status.IN_PROGRESS, epic2.getId());
        taskManager.updateSubtask(subtask22NewStatus);
        System.out.println("Измененные статусы Subtaskov Epic2: ✅ " + "Subtask21: " + subtask21.getStatus() +
                " Subtask22: " + subtask22.getStatus() + " Subtask23: " + subtask23.getStatus());
        System.out.println("Получение статуса Epic2: ✅" + epic2.getStatus());

        System.out.println("Печать всех ru.yandex.javacource.kuzmindv.schedule.task.Epic ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println("Печать всех Subtasks по Epic1 ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic1.getId())) {
            if(subtask == null){
                continue;
            }
            System.out.println(subtask.toString());
        }

        System.out.println("Добавление Subtask25✅");
        taskManager.createSubtask(subtask25, epic2);
        System.out.println("Печать всех ru.yandex.javacource.kuzmindv.schedule.task.Epic после добавление Subtask25 ✅");
        System.out.println(taskManager.findAllEpics());
        System.out.println("Печать всех Subtasks по Epic2 ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) {
            if(subtask == null){
                continue;
            }
            System.out.println(subtask.toString());
        }

        System.out.println("Удаление Subtask25 по id");
        taskManager.removeSubtaskById(subtask25.getId());
        System.out.println("Печать всех Subtasks по Epic2 ✅");
        for (Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) {
            if(subtask == null){
                continue;
            }
            System.out.println(subtask.toString());
        }

//        System.out.println("Удаление Epic1 ✅");
//        taskManager.removeEpicById(epic1.getId());
//        System.out.println("Печать всех ru.yandex.javacource.kuzmindv.schedule.task.Epic после удаления Epic1 ✅");
//        System.out.println(taskManager.findAllEpics());
//        for (ru.yandex.javacource.kuzmindv.schedule.task.Subtask subtask : taskManager.getSubtasksForEpic(epic1.getId())) {
//            if(subtask == null){
//                continue;
//            }
//            System.out.println(subtask.toString());
//        }
//
//        System.out.println("Удаление Epic2  по id ✅");
//        taskManager.removeEpicById(epic2.getId());
//        System.out.println("Печать всех ru.yandex.javacource.kuzmindv.schedule.task.Epic после удаления Epic2по id ✅");
//        System.out.println(taskManager.findAllEpics());
//        System.out.println("Печать всех Subtasks по Epic2 ✅");
//        for (ru.yandex.javacource.kuzmindv.schedule.task.Subtask subtask : taskManager.getSubtasksForEpic(epic2.getId())) {
//            if(subtask == null){
//                continue;
//            }
//            System.out.println(subtask.toString());
//        }
//
        System.out.println("Удаление всех Tusk, ru.yandex.javacource.kuzmindv.schedule.task.Epic,ru.yandex.javacource.kuzmindv.schedule.task.Subtask");
        taskManager.removeAll();
        System.out.println("Печать всех задач");
        System.out.println(taskManager.getTasks().toString());
        System.out.println(taskManager.findAllEpics());
        System.out.println(taskManager.findAllSubtasks());








    }
}
