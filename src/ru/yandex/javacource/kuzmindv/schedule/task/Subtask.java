package ru.yandex.javacource.kuzmindv.schedule.task;

public class Subtask extends Task {
    public Integer epicId;

    public Subtask(Integer id, String name, String description, Status status, Integer epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return  "Subtask{" + "epicId=" + epicId + super.toString() + '}' ;
    }
}
