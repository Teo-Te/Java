import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    
    public void init(Users user) {
        //Dashboardd
        JLabel labelDashboard = new JLabel("Dashboard", SwingConstants.CENTER);
        labelDashboard.setFont(font);
        //Welcome message
        JLabel labelWelcome = new JLabel("Welcome " + user.name, SwingConstants.CENTER);
        labelWelcome.setFont(font);
        //Buttons to navigate to other pages
        JButton buttonInsert = new JButton("Insert Tasks");
        buttonInsert.setFont(font);
        buttonInsert.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertTasks insertTasks = new InsertTasks();
                insertTasks.init(user);
            }
        });
        JButton buttonView = new JButton("View Tasks");
        buttonView.setFont(font);
        buttonView.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTasks viewTasks = new ViewTasks();
                viewTasks.init(user);
            }
        });
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.setFont(font);
        buttonLogout.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFormm loginForm = new LoginFormm();
                loginForm.init();
                dispose();
            }
        });
        //Add everything to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(labelDashboard);
        panel.add(labelWelcome);
        panel.add(buttonInsert);
        panel.add(buttonView);
        panel.add(buttonLogout);
        //Add the panel to the frame
        add(panel);
        //Set the frame properties
        setTitle("Main Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }//End init
    
    
}//End class

