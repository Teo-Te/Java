import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;

public class ViewTrash extends JFrame {
    private final Font font = new Font("Serif", Font.PLAIN, 20);

    public void init(Users user) {
        //Username
        String usn = user.getUsername();
        //Create the table
        JTable table = new JTable();
        table.setFont(font);
        table.setRowHeight(30);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setBackground(new Color(40, 40, 43));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to the frame.
        add(scrollPane, BorderLayout.CENTER);
        //Create the column names
        String columnNames[] = {"Due Date", "Task"};
        //Create the data
        String data[][] = new String[0][0];
        //Create the table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        //Set the model
        table.setModel(model);
        //Get the tasks from the database to display in the table
        getTasks(model, user.getUsername());
        //Create the buttons
        //Back button
        ButtonTemplate back = new ButtonTemplate("Back");
        back.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            }
        });
        //Delete button
        ButtonTemplate delete = new ButtonTemplate("Delete");
        delete.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            //Get the selected row
            int row = table.getSelectedRow();
            //Get the values from the selected row
            String duedate = table.getModel().getValueAt(row, 0).toString();
            String task = table.getModel().getValueAt(row, 1).toString();
            //Delete the task
            deleteTask(duedate, task);
            //Remove the row from the table
            model.removeRow(row);
            }
        });
        //Restore Button
        ButtonTemplate restore = new ButtonTemplate("Restore");
        restore.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            //Get the selected row
            int row = table.getSelectedRow();
            //Get the values from the selected row
            String duedate = table.getModel().getValueAt(row, 0).toString();
            String task = table.getModel().getValueAt(row, 1).toString();
            //Delete the task
            restoreTasks(task, duedate, usn);
            //Remove the row from the table
            model.removeRow(row);
            }
        });
        //Show current date and time
        JLabel date = new JLabel("Current date and time: " + new Date());
        date.setFont(font);
        date.setBackground(Color.WHITE);
        //Add the date label to the frame
        add(date, BorderLayout.NORTH);
        //Add the buttons to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(back);
        panel.add(delete);
        panel.add(restore);
        //Add the panel to frame
        add(panel, BorderLayout.SOUTH);

    
        //Set the frame properties
        setTitle("View Tasks");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.WHITE);
   }//End init
   
   //Database settings for getting the tasks
    public void getTasks(DefaultTableModel model, String user_name) {
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
            //Add the values to the table
            model.addRow(new Object[] {duedate, task});
            }//End while
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }//End try-catch
    }//End getTaskss
  
    //Database settings for deleting a task
    public void deleteTask(String duedate, String task) {
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
            e.printStackTrace();
        }//End try-catch
    }//End deleteTask

   //Database settings for restoring a task
   public static void restoreTasks(String task, String duedate, String usn){
        try {
            //connect to database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String sql = "INSERT INTO tasks (task, duedate, user_name) VALUES ('"+task+"', '"+duedate+"', '"+usn+"')";
            //Execute the query
            statement.executeUpdate(sql);
            //Show a message
            JOptionPane.showMessageDialog(null, "Task restored successfully!");
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }//End try-catch
    }//End restoreTasks
    
}//End class
