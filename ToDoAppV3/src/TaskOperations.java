import javax.swing.table.DefaultTableModel;

public interface TaskOperations {
    public void setTasks(String task, String duedate, String usn);
    public void getTasks(DefaultTableModel model, String user_name);
    public void deleteTask(String duedate, String task);
    public void sendToTrash(String task, String duedate, String usn);
    public void getTasksTrash(DefaultTableModel model, String user_name);
    public void deleteTaskTrash(String duedate, String task);
}
