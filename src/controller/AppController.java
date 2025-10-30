// The controller folder is where we put event listeners (i.e. addActionListner)
// When an event is detected, we change the view or can modify some objects from the model folder
// AppController name can be changed if necessary

package src.controller;

import java.util.Arrays;

import javax.security.auth.login.LoginException;
import javax.swing.JFrame;

import src.model.User;
import src.view.*;

public class AppController {
    // Instance variables for models and views
    // private AppState model;
    private LoginView loginView;
    private GymSelectionView selectionView;
    private SchedulerView schedulerView;

    // Constructor
    public AppController(LoginView loginView, GymSelectionView selectionView, SchedulerView schedulerView) {
        this.loginView = loginView;
        this.selectionView = selectionView;
        this.schedulerView = schedulerView;

        this.loginView.getLoginButton().addActionListener(e -> {
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
                try{
                    if(Arrays.asList(AU1, AU2, AU3, AU4).contains(inputUsername)) {
                        if(Arrays.equals(inputPassword, AP)) {
                            System.out.println("Login successful!");
                            loginFailed = false;
                        }
                        if(loginFailed == false) {
                            this.loginView.setVisible(false);
                            String[] gymNames = {"Gold's Gym", "24 Hour Fitness", "Planet Fitness", "LA Fitness", "Anytime Fitness", "Equinox", "Crunch Fitness", "YMCA", "Snap Fitness", "Life Time Fitness"};
                            this.selectionView.addComponents(gymNames);
                            this.selectionView.setVisible(true);
                            return; 
                        }
                    }
                    throw new LoginErrorException("Username or password incorrect.");
                }
                catch (LoginErrorException ex) {
                        System.out.println(ex.getMessage());
                        this.loginView.DisplayErrorMessageLabelVisible(ex.getMessage());
                        return; 
                }
            }
        });



        // After the user has selected a gym, display the scheduler view
        this.selectionView.getSelectionButton().addActionListener(e -> {
            System.out.println("Selected: " + this.selectionView.gymList.getSelectedValue());

            this.selectionView.repaint();

            this.selectionView.setVisible(false);
            this.schedulerView.setVisible(true);
        });

        // This section will handle the buttons in the scheduler view.
        // Normally createTeam would update the team model
        this.schedulerView.getCreateTeamButton().addActionListener(e -> {
            this.schedulerView.setVisible(false);
        });
    }


    // Call this method from main to start the app at the login screen
    public void start() {
        this.loginView.setVisible(true);
    }
}

class LoginErrorException extends Exception {
        public LoginErrorException(String message) {
            super(message);
        }
    }