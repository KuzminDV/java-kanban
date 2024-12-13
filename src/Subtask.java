public class Subtask extends Task{
    private Integer subtaskEpicId;

    public Subtask(Integer id, String name, String description, Status status, Integer EpicId) {
        super(id, name, description, status);
        this.subtaskEpicId = EpicId;
    }

    public Integer getSubtaskEpicId() {
        return subtaskEpicId;
    }

    public void setSubtaskEpicId(Integer subtaskEpicId) {
        this.subtaskEpicId = subtaskEpicId;
    }

//    @Override
//    public void setStatus(Status status) {
//        getSubtaskEpicId();
//        Ta
//    }

}
