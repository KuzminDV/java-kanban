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

    public void setSubtasksIds(Integer subtasksId) {
        this.subtasksIds.add(subtasksId);
    }

    @Override
    public String toString() {
        return "ru.yandex.javacource.kuzmindv.schedule.task.Epic{" +
                "subtasksIds=" + subtasksIds +
                '}';
    }


}
