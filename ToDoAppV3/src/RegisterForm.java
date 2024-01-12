import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterForm {
    public void init() {
        //Create the framee
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        //Create the font
        Font font = new Font("Arial", Font.PLAIN, 20);
        //Create the labels and text fields
       
        JLabel labelRegister = new JLabel("Register");
        labelRegister.setFont(font);

        JLabel labelName = new JLabel("Name: ");
        labelName.setFont(font);
        JTextField namee = new JTextField();
        namee.setFont(font);

        JLabel labelUsername = new JLabel("Username: ");
        labelUsername.setFont(font);
        JTextField userName = new JTextField();
        userName.setFont(font);

        JLabel labelPassword = new JLabel("Password: ");
        labelPassword.setFont(font);
        JPasswordField passWord = new JPasswordField();
        passWord.setFont(font);

        //Add everything to the panel
        panel.add(labelRegister);
        panel.add(labelName);
        panel.add(namee);
        panel.add(labelUsername);
        panel.add(userName);
        panel.add(labelPassword);
        panel.add(passWord);
        //Create the buttons
        JButton button = new JButton("Register");
        button.setFont(font);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namee.getText();
                String username = userName.getText();
                String password = String.valueOf(passWord.getPassword());
            
                    //Check if the username is already taken
                    if (getUser(username) == null) {
                        //If the username is not taken, register the user
                        registerUser(username, password, name);
                        //go back to login frame
                        frame.dispose();
                        LoginFormm login = new LoginFormm();
                        login.init();
                        
                    } else {
                        //If the username is taken, show an error message
                        JOptionPane.showMessageDialog(null, "Username is already taken, please try again");
                    }//End if-else
                
            }//End action performed
        });//End button action listener

        JButton button2 = new JButton("Back");
        button2.setFont(font);
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginFormm login = new LoginFormm();
                login.init();
            }
        });

         //Panel for the buttons
         JPanel panel2 = new JPanel();
         panel2.setLayout(new GridLayout(1, 2));
         panel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
         panel2.add(button);
         panel2.add(button2);
       
        //Show the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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