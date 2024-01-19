import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterForm extends JFrame {
    public void init() {
        //Create the font
        Font font = new Font("Arial", Font.PLAIN, 20);
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

                //Capitalize the first letter of the name
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                // Check if the username is already taken
                if (getUser(username) == null) {
                    // If the username is not taken, register the user
                    registerUser(username, password, name);
                    // Go back to login frame
                    dispose();
                    LoginFormm login = new LoginFormm();
                    login.init();
                } else {
                    // If the username is taken, show an error message
                    JOptionPane.showMessageDialog(null, "Username is already taken, please try again");
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
    //Database settings to get a user from the database
    public Users getUser(String username) {
        Users user = null;

        try{
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Get the user from the database
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            
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
    
    //Database settings to register a user
    public static void registerUser(String username, String password, String name) {
        try {
            //Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "arteofejzo");
            //Create the statement
            Statement statement = conn.createStatement();
            //Create the query
            String query = "INSERT INTO users (username, password, name) VALUES ('" + username + "', '" + password + "', '" + name + "')";
            //Execute the query
            statement.executeUpdate(query);
            //Close the connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }//End try-catch
    }//End registerUser
    
}//End class