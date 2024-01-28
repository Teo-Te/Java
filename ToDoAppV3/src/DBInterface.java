import javax.swing.table.DefaultTableModel;

public interface DBInterface {
    //Login and register methods
    public Users getUser(String username);
    public Users getUser(String username, String password);
    public void registerUser(String username, String password, String name);
    //Tasks methods
    public void getTasks(DefaultTableModel model, String user_name);
    public void deleteTask(String duedate, String task);
    public void sendToTrash(String task, String duedate, String usn, String time);
    public void updateTask(String currentTask, String currentDueDate, String currentDueTime, String updatedTask, String updatedDueDate, String usn, String updatedDueTime);
    public boolean searchTasks(DefaultTableModel model, String user_name, String search);
    //Trash methods
    public void getTasksTrash(DefaultTableModel model, String user_name);
    public void deleteTaskTrash(String duedate, String task);
    public void restoreTasks(String task, String duedate, String usn, String time);
    //Insert methods
    public void setTasks(String task, String duedate, String usn, String time);
    //Manage user methods
    public void updateUser(String name, String username, String password, String usn);
    public void deleteUser(String usn);
    public void deleteAllTasks(String usn);
    public void deleteAllTasksTrash(String usn);
    public void updateTaskUser(String oldUsn, String newUsn);
    public void updateTaskTrash(String oldUsn, String newUsn);
}
