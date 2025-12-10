/**
 * The LoginController is responsible for getting user credentials from the LoginView
 * and verifying the credentials match what's stored in the database.
 */

package src.controller;

import src.app.MainApp;
import src.view.LoginView;
import src.DBAdapter.Account_DAO;
import src.model.Account;

import java.util.Arrays;

public class LoginController {
    private LoginView loginView;
    private MainApp app;

    private Account_DAO account_dao;

    public LoginController(LoginView view, MainApp app) {
        this.loginView = view;
        this.app = app;

        account_dao = new Account_DAO();

        initController();
    }

    private void initController() {
        /**
         * Adds action listeners to buttons.
         */
        this.loginView.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        /**
         * Retrieves user credentials from the view and verifies that they match what's
         * stored in the database. If they don't match, a login failed message is displayed.
         */
        String inputEmail = this.loginView.getUserIDField();
        char[] inputPassword = this.loginView.getPassword();

        String stringPassword = new String(inputPassword); // Convert password returned by loginView to a String

        if (account_dao.validateCredentials(inputEmail, stringPassword)) {
            // If credentials are valid, display GymSelectionView.
            this.loginView.setVisible(false);
            app.showSelectionView();

            // After validation, save user credentials to main app instance variable
            // This way currentUser can be accessed from any controller that has an instance of main app passed to it
            Account currentUser = account_dao.getAccountByEmail(inputEmail);
            app.setCurrentUser(currentUser);
        }
        else {
            this.loginView.displayErrorMessageLabelVisible(); // Display error message if credentials don't match
        }
    }
}