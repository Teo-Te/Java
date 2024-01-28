import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    
    public void init(Users user) {
        //Background Image 
        ImageIcon background = new ImageIcon("src/images/bg3.png");
        JLabel bglabel = new JLabel(background) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        //Dashboard
        BigLabel labelDashboard = new BigLabel("Dashboard");
        //Welcome message
        JLabel labelWelcome = new JLabel("<html>Welcome <font color='red'><b>" + user.getName() + "</b></font></html>", SwingConstants.CENTER);
        labelWelcome.setFont(font);
        //Buttons to navigate to other pages
        ButtonTemplate buttonInsert = new ButtonTemplate("Insert Tasks");
        buttonInsert.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertTasks insertTasks = new InsertTasks();
                insertTasks.init(user);
            }
        });
        ButtonTemplate buttonView = new ButtonTemplate("View Tasks");
        buttonView.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTasks viewTasks = new ViewTasks();
                viewTasks.init(user);
            }
        });
        ButtonTemplate buttonLogout = new ButtonTemplate("Logout");
        buttonLogout.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFormm loginForm = new LoginFormm();
                loginForm.init();
                dispose();
            }
        });
        ButtonTemplate buttonTrash = new ButtonTemplate("Trash");
        buttonTrash.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTrash viewTrash = new ViewTrash();
                viewTrash.init(user);
            }
        });
        ButtonTemplate buttonUser = new ButtonTemplate("Manage your account");
        buttonUser.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUser manageUser = new ManageUser();
                manageUser.init(user);
                dispose();
            }
        });
        //Add everything to the bglabel
        bglabel.setLayout(new GridLayout(0, 1));
        bglabel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        bglabel.add(labelDashboard);
        bglabel.add(labelWelcome);
        bglabel.add(buttonInsert);
        bglabel.add(buttonView);
        bglabel.add(buttonTrash);
        bglabel.add(buttonUser);
        bglabel.add(buttonLogout);
        
        //Set the frame properties
        setContentPane(bglabel);
        setTitle("Main Page");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }//End init
    
}//End class

