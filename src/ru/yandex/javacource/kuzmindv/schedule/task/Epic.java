package ru.yandex.javacource.kuzmindv.schedule.task;
import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksIds;

    public Epic(Integer id, String name, String description, Status status) {
        super(id, name, description, status);
        this.subtasksIds = new ArrayList<>();
        status = Status.NEW;
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasksIds;
    }

    public void setSubtaskIds(Integer subtasksId) {
        this.subtasksIds.add(subtasksId);
    }

    public void setSubtasksIds(ArrayList<Integer> subtasksIds) {
        this.subtasksIds = subtasksIds;
    }



    @Override
    public String toString() {
        return "Epic{" + "subtasksIds=" + subtasksIds + super.toString() + '}';
    }
}
