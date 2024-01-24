import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DBOperations implements DBInterface{
    /*Note that some of these methods have Statements and some have PreparedStatements
    Some of these methods were made last year, when I was still learning about SQL 
    and I didn't know about PreparedStatements, so I used Statements instead.
    since it is not the purpose of this project to learn about SQL, I didn't change them.
    P.S I'm not that good at using SQL yet.
    */

    public Users getUser(String username, String password) {
        Users user = null;

        try{
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Get the user from the database
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet result = statement.executeQuery();
            //If the user exists, create a new user object
            if (result.next()) {
                user = new Users();
                user.setName(result.getString("name"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                
            }
            //Close the connection
            statement.close(); 
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
        return user;
    }//End getUser

    public Users getUser(String username) {
        Users user = null;

        try{
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Get the user from the database
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            
            ResultSet result = statement.executeQuery();
            //If the user exists, create a new user object
            if (result.next()) {
                user = new Users();
                user.setName(result.getString("name"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                
            }
            //Close the connection
            statement.close(); 
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
        return user;
    }//End getUser
    
    //Database settings to register a user
    @Override
    public void registerUser(String username, String password, String name) {
        try {
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String query = "INSERT INTO users (username, password, name) VALUES ('" + username + "', '" + password + "', '" + name + "')";
            //Execute the query
            statement.executeUpdate(query);
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End registerUser

    @Override
    public void getTasks(DefaultTableModel model, String user_name) {
        try {
          //Connect to the database
          Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
          //Create the statement
          Statement statement = conn.createStatement();
          //Create the query
          String query = "SELECT * FROM tasks WHERE user_name = '" + user_name + "'";
          //Execute the query
          ResultSet rs = statement.executeQuery(query);
          //Loop through the results
          while (rs.next()) {
            //Get the values from the current row
            String duedate = rs.getString("duedate");
            String task = rs.getString("task");
            String duetime = rs.getString("duetime");
            //Add the values to the table
            model.addRow(new Object[] {duedate, task, duetime});
          }//End while
          //Close the connection
          statement.close();
          conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End getTaskss
  
    //Database settings for deleting a task
    @Override
    public void deleteTask(String duedate, String task) {
        try {
          //Connect to the database
          Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
          //Create the statement
          Statement statement = conn.createStatement();
          //Create the query
          String query = "DELETE FROM tasks WHERE duedate = '" + duedate + "' AND task = '" + task + "'";
          //Execute the query
          statement.executeUpdate(query);
          //Show a message
          JOptionPane.showMessageDialog(null, "Task is sent to trash!");
          //Close the connection
          statement.close();
          conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End deleteTask

    //Database settings for sending a task to trash
    @Override
    public void sendToTrash(String task, String duedate, String usn, String time){
        try {
            //connect to database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String sql = "INSERT INTO trash (task, duedate, user_name, duetime) VALUES ('"+task+"', '"+duedate+"', '"+usn+"', '"+time+"')";
            //Execute the query
            statement.executeUpdate(sql);
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//end sendToTrash

    //Database settings for updating a task
    @Override
    public void updateTask(String currentTask, String currentDueDate, String currentDueTime, 
    String updatedTask, String updatedDueDate, String usn, String updatedDueTime) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");

            // Create the query
            String sql = "UPDATE tasks SET task = ?, duedate = ?, duetime = ? WHERE user_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // Set parameters to avoid SQL injection
            preparedStatement.setString(1, updatedTask);
            preparedStatement.setString(2, updatedDueDate);
            preparedStatement.setString(3, updatedDueTime);
            preparedStatement.setString(4, usn);
            // Execute the query
            preparedStatement.executeUpdate();
            

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }
    }//End updateTask

     //Database settings for getting the tasks
     @Override
     public void getTasksTrash(DefaultTableModel model, String user_name) {
        try {
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String query = "SELECT * FROM trash WHERE user_name = '" + user_name + "'";
            //Execute the query
            ResultSet rs = statement.executeQuery(query);
            //Loop through the results
            while (rs.next()) {
            //Get the values from the current row
            String duedate = rs.getString("duedate");
            String task = rs.getString("task");
            String duetime = rs.getString("duetime");
            //Add the values to the table
            model.addRow(new Object[] {duedate, task, duetime});
            }//End while
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End getTaskss
  
    //Database settings for deleting a task
    @Override
    public void deleteTaskTrash(String duedate, String task) {
        try {
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String query = "DELETE FROM trash WHERE duedate = '" + duedate + "' AND task = '" + task + "'";
            //Execute the query
            statement.executeUpdate(query);
            //Show a message
            JOptionPane.showMessageDialog(null, "Task deleted successfully!");
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End deleteTask

   //Database settings for restoring a task
   @Override
   public void restoreTasks(String task, String duedate, String usn, String time) {
        try {
            //connect to database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String sql = "INSERT INTO tasks (task, duedate, user_name, duetime) VALUES ('"+task+"', '"+duedate+"', '"+usn+"', '"+time+"')";
            //Execute the query
            statement.executeUpdate(sql);
            //Show a message
            JOptionPane.showMessageDialog(null, "Task restored successfully!");
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End restoreTasks

    @Override
    public void setTasks(String task, String duedate, String usn, String time) {
        try {
            //connect to database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String sql = "INSERT INTO tasks (task, duedate, user_name, duetime) VALUES ('"+task+"', '"+duedate+"', '"+usn+"', '"+time+"')";
            //Execute the query
            statement.executeUpdate(sql);
            //Show a message
            JOptionPane.showMessageDialog(null, "Task inserted successfully!");
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operation canceled!");
        }//End try-catch
    }//End setTasks

}
