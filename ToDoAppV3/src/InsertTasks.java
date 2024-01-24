import javax.swing.*;
import org.jdatepicker.JDatePicker;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class InsertTasks extends JFrame {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    //DB Operations instance
    DBOperations db = new DBOperations();
    //Validation instance
    Validate validation = new Validate();
    public void init(Users user){
        //Task label and text field
        SmallLabel tasks = new SmallLabel("Insert new task: ");
        
        JTextField newTask = new JTextField();
        newTask.setFont(font);
        //Due date label and text field
        SmallLabel dueDate = new SmallLabel("Insert due date and time: ");

        JDatePicker jDatePicker1 = new JDatePicker();
        jDatePicker1.setFont(font);

        JTextField time = new JTextField();
        time.setFont(font);
        
        //Buttons
        ButtonTemplate insert = new ButtonTemplate("Insert");

        insert.onClick(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
                String task = newTask.getText();
                String duedate = jDatePicker1.getFormattedTextField().getText();
                String usn = user.getUsername();
                String time1 = time.getText();
                if (!validation.validateTime(time1)) {
                    return;
                } else if (task.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a task");
                    return;
                } else if (duedate.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a due date");
                    return;
                } else if (time1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a due time");
                    return;
                }
                
                db.setTasks(task, duedate, usn, time1);
            }
        });
    
        ButtonTemplate back = new ButtonTemplate("Back");
        back.onClick((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Show current date and time
        JLabel date = new JLabel("" + new Date());
        date.setFont(font);
        

        //Add the labels and text fields to a panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 1));
        panel1.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel1.setBackground(Color.WHITE);
        panel1.add(date);
        panel1.add(tasks);
        panel1.add(newTask);
        panel1.add(dueDate);
        panel1.add(jDatePicker1);
        panel1.add(time);
        //Add the buttons to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(insert);
        panel.add(back);
        
        //Add the panels to the frame
        add(panel, BorderLayout.SOUTH);
        add(panel1, BorderLayout.NORTH);
        //Set the frame properties
        setTitle("Insert Tasks");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setVisible(true);
    }//End init

}//End class