// The controller folder is where we put event listeners (i.e. addActionListner)
// When an event is detected, we change the view or can modify some objects from the model folder
// AppController name can be changed if necessary

package src.controller;

import java.util.Arrays;

import javax.swing.JFrame;

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

        // Login screen is displayed when start() method is called from main
        // When the user 'logs in', we want to hide the login window and show the gym selection view
        this.loginView.getLoginButton().addActionListener(e -> {
            //get username and password from text fields
            String AU1 = "Marie"; 
            String AU2 = "Benjamin"; 
            String AU3 = "Sarah"; 
            String AU4 = "Logan"; 
            char[] AP = {'p','a','s','s','w','o','r','d'};
            boolean loginSuccess = false;

                /*This section is to test the login function
                 * - Right now if the wrong username or password is entered, error message prints to the terminal and not the page itself(need to fix)
                 */
                String inputUsername = this.loginView.getUserIDField();
                char[] inputPassword = this.loginView.getPassword();

                while(!loginSuccess){
                    if(Arrays.asList(AU1, AU2, AU3, AU4).contains(inputUsername)) {
                if(Arrays.equals(inputPassword, AP)) {
                    System.out.println("Login successful!");
                }
            try{
           /*  String inputUsername = this.loginView.getUserIDField();
            char[] inputPassword = this.loginView.getPassword();
            if(Arrays.asList(AU1, AU2, AU3, AU4).contains(inputUsername)) {
                if(Arrays.equals(inputPassword, AP)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Incorrect password.");
                    return;
                }*/
            loginSuccess = true;
            this.loginView.setVisible(false);
            this.selectionView.setVisible(true);
        }
         catch (Exception ex) {
            System.out.println("Username or password incorrect: " + ex.getMessage());
            throw ex;
        }
    
    }

        

    });



        // After the user has selected a gym, display the scheduler view
        this.selectionView.getSelectionButton().addActionListener(e -> {
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
