package src.controller;

import src.view.LoginView;
import src.app.MainApp;
import src.DBAdapter.Account_DAO;
import src.model.Account;

import java.util.Arrays;

public class LoginController {
    private LoginView loginView;
    private MainApp app;

    private Account_DAO account_dao = new Account_DAO();

    public LoginController(LoginView view, MainApp app) {
        this.loginView = view;
        this.app = app;

        initController();
    }

    private void initController() {
        this.loginView.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        Account_DAO account_access = new Account_DAO();

        String inputUsername = this.loginView.getUserIDField();
        char[] inputPassword = this.loginView.getPassword();

        String stringPassword = new String(inputPassword); // convert password returned by loginView to a String

        if (account_access.validateCredentials(inputUsername, stringPassword)) {
            System.out.println("Login verified!");
            this.loginView.setVisible(false);
            app.showSelectionView();

            // After validation, save user credentials to main app instance variable
            // This way currentUser can be accessed from any controller that has an instance of main app passed to it
            Account currentUser = account_dao.getAccountByEmail(inputUsername);
            app.setCurrentUser(currentUser);
            
            return;
        }
        else {
            System.out.println("Login failed");
            this.loginView.displayErrorMessageLabelVisible();
            return; 
        }
    }
}

class LoginErrorException extends Exception {
    public LoginErrorException(String message) {
        super(message);
    }
}
