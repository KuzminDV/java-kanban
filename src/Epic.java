import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private Integer epicId;
    private ArrayList<Subtask> subtasks;

    public Epic(Integer id, String name, String description, Status status, Integer epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
        this.subtasks = new ArrayList<>();
        status = Status.NEW;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
    }

    public void removeSubtask(Subtask subtask) {
        this.subtasks.remove(subtask);
    }

}
