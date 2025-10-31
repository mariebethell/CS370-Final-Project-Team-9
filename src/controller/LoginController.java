package src.controller;

import src.view.LoginView;
import src.app.MainApp;

import java.util.Arrays;

public class LoginController {
    private LoginView loginView;
    private MainApp app;

    public LoginController(LoginView view, MainApp app) {
        this.loginView = view;
        this.app = app;

        initController();
    }

    private void initController() {
        this.loginView.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        //get username and password from text fields
        String AU1 = "Marie"; 
        String AU2 = "Benjamin"; 
        String AU3 = "Sarah"; 
        String AU4 = "Logan"; 
        char[] AP = {'p','a','s','s','w','o','r','d'};
        boolean loginFailed = true;

        while(loginFailed == true) {
            /*This section is to test the login function
            * - Right now if the wrong username or password is entered, error message prints to the terminal and not the page itself(need to fix)
            */
            String inputUsername = this.loginView.getUserIDField();
            char[] inputPassword = this.loginView.getPassword(); 
            try {
                if(Arrays.asList(AU1, AU2, AU3, AU4).contains(inputUsername)) {
                    if(Arrays.equals(inputPassword, AP)) {
                        System.out.println("Login successful!");
                        loginFailed = false;
                    }
                    if(loginFailed == false) {
                        this.loginView.setVisible(false);
                        String[] gymNames = {"Gold's Gym", "24 Hour Fitness", "Planet Fitness", "LA Fitness", "Anytime Fitness", "Equinox", "Crunch Fitness", "YMCA", "Snap Fitness", "Life Time Fitness"};
                        app.showSelectionView();
                        return; 
                    }
                }
                throw new LoginErrorException("Username or password incorrect.");
            }
            catch (LoginErrorException ex) {
                    System.out.println(ex.getMessage());
                    this.loginView.displayErrorMessageLabelVisible(ex.getMessage());
                    return; 
            }
        }
    }
}

class LoginErrorException extends Exception {
    public LoginErrorException(String message) {
        super(message);
    }
}
