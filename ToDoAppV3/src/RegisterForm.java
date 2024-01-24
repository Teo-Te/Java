import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterForm extends JFrame {
    public void init() {
        //Create the font
        Font font = new Font("Arial", Font.PLAIN, 20);

        //DB Operations instance
        DBOperations db = new DBOperations();
        //Create the labels and text fields
       
        BigLabel labelRegister = new BigLabel("Register");

        SmallLabel labelName = new SmallLabel("Name: ");

        JTextField namee = new JTextField();
        namee.setFont(font);

        SmallLabel labelUsername = new SmallLabel("Username: ");

        JTextField userName = new JTextField();
        userName.setFont(font);

        SmallLabel labelPassword = new SmallLabel("Password: ");

        JPasswordField passWord = new JPasswordField();
        passWord.setFont(font);

        //ToDoApp logo image 
        ImageIcon logo = new ImageIcon("src/images/bg1.png");
        JLabel logoLabel = new JLabel(logo) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(logo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        logoLabel.setPreferredSize(new Dimension(50, 200));
        //Panel for the logo
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 150, 5, 150));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.add(logoLabel);

        //Add everything to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(1, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(labelRegister);
        panel.add(labelName);
        panel.add(namee);
        panel.add(labelUsername);
        panel.add(userName);
        panel.add(labelPassword);
        panel.add(passWord);
        //Create the buttons
        ButtonTemplate button = new ButtonTemplate("Register");

        button.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namee.getText();
                String username = userName.getText();
                String password = String.valueOf(passWord.getPassword());

                //Validate the username, password and name
                Validate validate = new Validate();
                if (!validate.validateUsername(username) || !validate.validatePassword(password) || !validate.validateName(name)){
                    return;
                }

                //Capitalize the first letter of the name
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                // Check if the username is already taken
                if (db.getUser(username) == null) {
                    // If the username is not taken, register the user
                    db.registerUser(username, password, name);
                    // Go back to login frame
                    JOptionPane.showMessageDialog(null, "You have successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    LoginFormm login = new LoginFormm();
                    login.init();
                } else {
                    // If the username is taken, show an error message
                    JOptionPane.showMessageDialog(null, "Username is already taken, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }// End action performed
        });//End button action listener

        ButtonTemplate button2 = new ButtonTemplate("Back");
        
        button2.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFormm login = new LoginFormm();
                login.init();
            }
        });

        //Panel for the buttons
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
        panel2.setBackground(Color.WHITE);
        panel2.add(button);
        panel2.add(button2);

        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(logoPanel, BorderLayout.NORTH);
       
        //Frame settings
        setTitle("Register");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(550, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }//End init
    
}//End class