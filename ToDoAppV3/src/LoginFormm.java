import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFormm extends JFrame {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    public void init() {
        // Form Layoutt
        JLabel labelLogin = new JLabel("Login Page", SwingConstants.CENTER);
        labelLogin.setFont(font);
        //Username labels and text field
        JLabel labelUsername = new JLabel("Username: ");
        labelUsername.setFont(font);
        JTextField userName = new JTextField();
        userName.setFont(font);
        //Password labels and text field
        JLabel labelPassword = new JLabel("Password: ");
        labelPassword.setFont(font);
        JPasswordField passWord = new JPasswordField();
        passWord.setFont(font);
        //Add everything to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(labelLogin);
        panel.add(labelUsername);
        panel.add(userName);
        panel.add(labelPassword);
        panel.add(passWord);
        //Register button
        JButton buttonReg = new JButton("Register");
        buttonReg.setFont(font);
        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.init();
                dispose();
            }
        });
        //The Login button
        JButton button = new JButton("Login");
        button.setFont(font);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                String password = String.valueOf(passWord.getPassword());
                //Get the user from the database
                Users user = getUser(username, password);

                if (user != null) {
                    //If the user exists, open the main frame
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.init(user);
                    //Close the login frame
                    dispose();
                } else {
                    //If the user does not exist, show an error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password, please try again");
                }//End if-else
            }//End action performed
        });//End button action listener

        //Close button
        JButton button2 = new JButton("Close");
        button2.setFont(font);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Panel for the buttons
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
        panel2.add(button);
        panel2.add(button2);
        panel2.add(buttonReg);

        //Add panel to frame
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        //Frame settings
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setMinimumSize(new Dimension(300, 100));
        setLocationRelativeTo(null);
        setVisible(true);
    }//End init
    //Database settings
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
                user.name = result.getString("name");
                user.username = result.getString("username");
                user.password = result.getString("password");
                
            }
            //Close the connection
            statement.close(); 
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }//End try-catch
        return user;
    }//End getUser
    
}//End class



