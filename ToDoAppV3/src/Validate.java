import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Validate implements ValidatorInterface {
    public boolean validateUsername(String username) {
        //Using the StringBuilder class to create a regex
        //The common practice is to use String for the regex but it was required to use StringBuilder
        StringBuilder regexBuilder = new StringBuilder("^");
        //It will not accept lowercase and spaces
        regexBuilder.append("[a-z]+");  
        regexBuilder.append("$"); 
        String regex = regexBuilder.toString();

        //Create the pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validatePassword(String password) {
        //Using the StringBuffer class to create a regex
        StringBuffer regexBuffer = new StringBuffer("^");
        regexBuffer.append("(?=.*[a-z])");  //Lowercase letter
        regexBuffer.append("(?=.*[A-Z])");  //Uppercase letter
        regexBuffer.append("(?=.*\\d)");     //Digit
        regexBuffer.append("(?=\\S+$)");  //No spaces
        regexBuffer.append(".{8,}");  //Minimum 8 characters
        regexBuffer.append("$");

        String regex = regexBuffer.toString();
        //Create the pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validateName(String name) {
        String regex = "^[a-zA-Z]+$";

        //Create the pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Name is not valid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validateTime(String time) {
        String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

        //Create the pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);

        if (matcher.matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Time is not in valid, hh:mm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validateDate(String date) {
        String regex = "^(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{2}\\s\\d{4}$";

        //Create the pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Date is not valid, mmm dd yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
