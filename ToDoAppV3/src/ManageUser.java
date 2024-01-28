import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ManageUser extends JFrame {
    private final Font font = new Font("Serif", Font.PLAIN, 20);
    //DB Operations instance
    DBOperations db = new DBOperations();
    //Validation instance
    Validate validate = new Validate();
    public void init (Users user){
        //Username
        String usn = user.getUsername();
        //Message
        JLabel message = new JLabel("Leave the fields blank if you don't want to change them", SwingConstants.CENTER);
        //Name label and text field
        SmallLabel name = new SmallLabel("<html>Name:  <font color='red'><b>" + user.getName() + "</b></font></html>");
        JTextField newName = new JTextField();
        newName.setFont(font);
        //Username label and text field
        SmallLabel username = new SmallLabel("<html>Username:  <font color='red'><b>" + usn + "</b></font></html>");
        JTextField newUsername = new JTextField();
        newUsername.setFont(font);
        //Password label and text field
        SmallLabel password = new SmallLabel("Password: ");
        JPasswordField newPassword = new JPasswordField();
        newPassword.setFont(font);
        //Buttons
        ButtonTemplate update = new ButtonTemplate("Update");
        update.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1 = newName.getText();
                String username1 = newUsername.getText();
                String password1 = String.valueOf(newPassword.getPassword());

                if (name1.equals("")) {
                    name1 = user.getName();
                } else if (!validate.validateName(name1)) {
                    return;
                }

                if (username1.equals("")) {
                    username1 = usn;
                } else if (!validate.validateUsername(username1)) {
                    return;
                } else if (db.getUser(username1) != null) {
                    JOptionPane.showMessageDialog(null, "Username is already taken, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password1.equals("")) {
                    password1 = user.getPassword();
                } else if (!validate.validatePassword(password1)) {
                    return;
                }
                
                String passW = JOptionPane.showInputDialog("Enter your current password to confirm changes");
                if (!passW.equals(user.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                db.updateUser(name1, username1, password1, usn);
                db.updateTaskUser(usn, username1);
                db.updateTaskTrash(usn, username1);
                JOptionPane.showMessageDialog(null, "You have successfully updated your account", "Success", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Please login again", "Success", JOptionPane.INFORMATION_MESSAGE);
                LoginFormm loginForm = new LoginFormm();
                loginForm.init();
                dispose();
            }
        });
        ButtonTemplate delete = new ButtonTemplate("Delete Account");
        delete.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = JOptionPane.showInputDialog("Enter your password to confirm deletion");
                if (!pass.equals(user.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                db.deleteUser(usn);
                db.deleteAllTasks(usn);
                db.deleteAllTasksTrash(usn);
                LoginFormm loginForm = new LoginFormm();
                loginForm.init();
                dispose();
            }
        });
        ButtonTemplate back = new ButtonTemplate("Back");
        back.onClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.init(user);
                dispose();
            }
        });
        //Add everything to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        panel.setBackground(Color.WHITE);
        panel.add(message);
        panel.add(name);
        panel.add(newName);
        panel.add(username);
        panel.add(newUsername);
        panel.add(password);
        panel.add(newPassword);
        panel.add(update);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel2.setBackground(Color.WHITE);
        panel2.add(delete);
        panel2.add(back);
        //Add the panel to the frame
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        //Set the frame properties
        setTitle("Manage your account");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
