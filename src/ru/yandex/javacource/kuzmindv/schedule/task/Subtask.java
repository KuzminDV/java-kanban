package ru.yandex.javacource.kuzmindv.schedule.task;

public class Subtask extends Task {
    public Integer epicId;

    public Subtask(Integer id, String name, String description, Status status, Integer EpicId) {
        super(id, name, description, status);
        this.epicId = EpicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

//    @Override
//    public String toString() {
//        return "ru.yandex.javacource.kuzmindv.schedule.task.Subtask{" +
//                "epicId=" + epicId +
//                '}';
//    }

}
