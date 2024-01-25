import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class ViewTasks extends JFrame {
    private final Font font = new Font("Serif", Font.PLAIN, 20);
    //DB Operations instance
    DBOperations db = new DBOperations();
    //Validation instance
    Validate validation = new Validate();
    public void init(Users user){
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
        String columnNames[] = {"Due Date", "Task", "Time"};
        //Create the data
        String data[][][] = new String[0][0][0];
        //Create the table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        //Set the model
        table.setModel(model);
        //Get the tasks from the database to display in the table
        db.getTasks(model, usn);

        //Search bar
        JTextField search = new JTextField();
        search.setFont(font);
        
        ButtonTemplate searchButton = new ButtonTemplate("Search Task");
        searchButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String searchValue = search.getText();
            //Check if searchValue is empty so as to reset the table
            if (searchValue.equals("")) {
              model.setRowCount(0);
              db.getTasks(model, usn);
              return;
            }
            
            //Check if any matching tasks were found
            if (!db.searchTasks(model, usn, searchValue)) {
              JOptionPane.showMessageDialog(null, "No matching tasks found");
              return;
            }

            //Clear the table
            model.setRowCount(0);
            //Get the tasks from the database to display in the table
            db.searchTasks(model, usn, searchValue);
          }
        });
        
        //Back button
        ButtonTemplate back = new ButtonTemplate("Back");
        back.addActionListener((ActionListener) new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        });

        //Delete button
        ButtonTemplate delete = new ButtonTemplate("Send to trash");
        delete.addActionListener((ActionListener) new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
            //Get the selected row
            int row = -1;
            row = table.getSelectedRow();

            //Check if a row is selected
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Get the values from the selected row
            String duedate = table.getModel().getValueAt(row, 0).toString();
            String task = table.getModel().getValueAt(row, 1).toString();
            String time = table.getModel().getValueAt(row, 2).toString();
            //Delete the task
            db.deleteTask(duedate, task);
            db.sendToTrash(task, duedate, usn, time);
            //Remove the row from the table
            model.removeRow(row);
          }
        });

        ButtonTemplate update = new ButtonTemplate("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the selected row
                int row = -1;
                row = table.getSelectedRow();

                //Check if a row is selected
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //Get the values from the selected row
                String dueDate = table.getValueAt(row, 0).toString();
                String task = table.getValueAt(row, 1).toString();
                String time = table.getValueAt(row, 2).toString();

                //Show input dialog to get updated values
                String updatedDueDate = JOptionPane.showInputDialog("Enter updated due date:", dueDate);
                if(!validation.validateDate(updatedDueDate)) {
                  return;
                }
                String updatedTime = JOptionPane.showInputDialog("Enter updated time:", time);
                if(!validation.validateTime(updatedTime)) {
                  return;
                }
                String updatedTask = JOptionPane.showInputDialog("Enter updated task:", task);
                if(updatedTask.equals("")) {
                  JOptionPane.showMessageDialog(null, "Please enter a task");
                  return;
                }
                
                //Update the task in the database
                db.updateTask(task, dueDate, time,  updatedTask, updatedDueDate, usn, updatedTime);

                //Update the values in the table
                table.setValueAt(updatedDueDate, row, 0);
                table.setValueAt(updatedTask, row, 1);
            }
        });

        //Show current date and time
        JLabel date = new JLabel("Current date and time: " + new Date());
        date.setFont(font);
        date.setBackground(Color.WHITE);
        //Add the date label to the frame
        add(date, BorderLayout.NORTH);
        //Add the search bar and button to a panel
        //Create a panel with GridBagLayout
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        GridBagConstraints constraints = new GridBagConstraints();

        //The search component to take 2/3 of the space
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(search, constraints);

        //The button to take 1/3 of the space
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        searchPanel.add(searchButton, constraints);

        //Add the buttons to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(back);
        panel.add(delete);
        panel.add(update);
        //Add the panel to frame
        add(panel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.NORTH);
      
        //Set the frame properties
        setTitle("View Tasks");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.WHITE);
    }//end init
    
}//End class
