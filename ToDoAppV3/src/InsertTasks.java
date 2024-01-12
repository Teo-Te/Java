import javax.swing.*;
import org.jdatepicker.JDatePicker;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class InsertTasks extends JFrame {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    public void init(Users user){
        //Task label and text field
        JLabel tasks = new JLabel("Insert new task: ");
        tasks.setFont(font);
        JTextField newTask = new JTextField();
        newTask.setFont(font);
        //Due date label and text field
        JLabel dueDate = new JLabel("Insert due date: ");
        dueDate.setFont(font);
        JDatePicker jDatePicker1 = new JDatePicker();
        jDatePicker1.setFont(font);
        
        //Buttons
        JButton insert = new JButton("Insert");
        insert.setFont(font);

        insert.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
                String task = newTask.getText();
                String duedate = jDatePicker1.getFormattedTextField().getText();
                String usn = user.username;
                
                setTasks(task, duedate, usn);
            }
        });
    
        JButton back = new JButton("Back");
        back.setFont(font);
        back.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Add the labels and text fields to a panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 1));
        panel1.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel1.add(tasks);
        panel1.add(newTask);
        panel1.add(dueDate);
        panel1.add(jDatePicker1);
        //Add the buttons to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(insert);
        panel.add(back);
        
        //Add the panels to the frame
        add(panel, BorderLayout.SOUTH);
        add(panel1, BorderLayout.NORTH);
        //Set the frame properties
         setTitle("Insert Tasks");
         setSize(500, 300);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
    }//End init
    //Database settingss
    public static void setTasks(String task, String duedate, String usn){
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
            JOptionPane.showMessageDialog(null, "Task inserted successfully!");
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}//End class