package src.view;
import javax.swing.*;
//import src.model.User;

import java.awt.*;


public class LoginView extends JFrame {
    private JButton loginButton = new JButton("Login");
    private JLabel UserIDLabel = new JLabel("Username");
    private JLabel PasswordLabel = new JLabel("Password"); 
    private static JTextField UserIDField = new JTextField(10);
    private static JPasswordField PasswordField = new JPasswordField(10); 


    //PasswordField = this.LoginView().getLoginPanel().JPasswordField;
    // Constructor
    public LoginView() {
        setTitle("Login");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        add(loginButton);

        /* 
        UserIDLabel.setLabelFor(UserIDField);
        add(UserIDLabel);
        add(UserIDField);
        

        PasswordLabel.setLabelFor(PasswordField);
        add(PasswordLabel);
        add(PasswordField);
        */
        add(getLoginPanel());
    }

    public JButton getLoginButton() {
        return loginButton;
    }
    public JPanel getLoginPanel() {
    JPanel panel = new JPanel(); 
        panel.add(UserIDLabel, BorderLayout.CENTER);
        panel.add(UserIDField, BorderLayout.CENTER);
        panel.add(PasswordLabel, BorderLayout.CENTER);
        panel.add(PasswordField, BorderLayout.CENTER);
        return panel;
    }
    public static String getUserIDField() {
        String UV = UserIDField.getText();
        return UV;
    }

        public JPasswordField getPasswordJField() {
        return PasswordField;
        }

    public char[] getPassword() {
        return PasswordField.getPassword();
    }   

}