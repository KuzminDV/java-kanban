public class Subtask extends Task{
    private Integer subtaskEpicId;

    public Subtask(Integer id, String name, String description, Status status, Integer EpicId) {
        super(id, name, description, status);
        this.subtaskEpicId = EpicId;
    }
}
