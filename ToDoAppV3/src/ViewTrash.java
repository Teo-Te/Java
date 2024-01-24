import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class ViewTrash extends JFrame {
    private final Font font = new Font("Serif", Font.PLAIN, 20);
    //DB Operations instance
    DBOperations db = new DBOperations();

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
        String columnNames[] = {"Due Date", "Task", "Due Time"};
        //Create the data
        String data[][][] = new String[0][0][0];
        //Create the table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        //Set the model
        table.setModel(model);
        //Get the tasks from the database to display in the table
        db.getTasksTrash(model, user.getUsername());
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
            int row = -1;
            row = table.getSelectedRow();

            // Check if a row is selected
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Get the values from the selected row
            String duedate = table.getModel().getValueAt(row, 0).toString();
            String task = table.getModel().getValueAt(row, 1).toString();
            //Delete the task
            db.deleteTaskTrash(duedate, task);
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
            int row = -1;
            row = table.getSelectedRow();

            // Check if a row is selected
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Get the values from the selected row
            String duedate = table.getModel().getValueAt(row, 0).toString();
            String task = table.getModel().getValueAt(row, 1).toString();
            String time = table.getModel().getValueAt(row, 2).toString();
            //Delete the task
            db.restoreTasks(task, duedate, usn, time);
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
    
}//End class
