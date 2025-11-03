package src.view;
import javax.swing.*;

import java.awt.*;

public class LoginView extends JFrame {
    private JButton loginButton = new JButton("Login");
    private JLabel UserIDLabel = new JLabel("Username");
    private JLabel PasswordLabel = new JLabel("Password"); 
    private JTextField UserIDField = new JTextField(10);
    private JPasswordField PasswordField = new JPasswordField(10); 
    private JLabel errorLabel = new JLabel("Incorrect Username or Password");  

    // Constructor
    public LoginView() {
        setTitle("Login");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        add(getLoginPanel());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JPanel getLoginPanel() {
        JPanel panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(UserIDLabel);
        panel.add(UserIDField);
        panel.add(PasswordLabel);
        panel.add(PasswordField);
        panel.add(loginButton);
        panel.add(errorLabel);

        errorLabel.setVisible(false);
        return panel;
    }
    public String getUserIDField() {
      //  String UV = UserIDField.getText();
        return UserIDField.getText();
    }

    public char[] getPassword() {
        return PasswordField.getPassword();
    }   

    public String displayErrorMessageLabel(){
        return "Username or password incorrect.";
    }

    public void displayErrorMessageLabelVisible(String Message){
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(true);
    }

}
