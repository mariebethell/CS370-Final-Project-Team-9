package src.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JButton loginButton = new JButton("Login");

    // Constructor
    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        add(loginButton);
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
