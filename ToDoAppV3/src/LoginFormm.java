import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFormm extends JFrame implements LoginOperations {
    final private Font font = new Font("Serif", Font.PLAIN, 20);
    public void init() {
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
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.add(logoLabel);

        //Login label
        BigLabel labelLogin = new BigLabel("Login Page");
        //Username labels and text field
        SmallLabel labelUsername = new SmallLabel("Username: ");
        //Username text field
        JTextField userName = new JTextField();
        userName.setFont(font);
        //Password labels and text field
        SmallLabel labelPassword = new SmallLabel("Password: ");

        JPasswordField passWord = new JPasswordField();
        passWord.setFont(font);
        //Add everything to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(labelLogin);
        panel.add(labelUsername);
        panel.add(userName);
        panel.add(labelPassword);
        panel.add(passWord);
        //Register button
        ButtonTemplate buttonReg = new ButtonTemplate("Register");
        buttonReg.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.init();
                dispose();
            }
        });
        //The Login button
        ButtonTemplate button = new ButtonTemplate("Login");
        button.onClick(new ActionListener() {
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
        ButtonTemplate button2 = new ButtonTemplate("Close");
        button2.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Panel for the buttons
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
        panel2.setBackground(Color.WHITE);
        panel2.add(button);
        panel2.add(button2);
        panel2.add(buttonReg);

        //Add panel to frame
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(logoPanel, BorderLayout.NORTH);
        //Frame settings
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 600);
        setMinimumSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }//End init
    //Database settings
    @Override
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
            e.printStackTrace();
        }//End try-catch
        return user;
    }//End getUser
    
}//End class



