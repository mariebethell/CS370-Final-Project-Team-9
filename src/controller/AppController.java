// The controller folder is where we put event listeners (i.e. addActionListner)
// When an event is detected, we change the view or can modify some objects from the model folder
// AppController name can be changed if necessary

package src.controller;

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
            this.loginView.setVisible(false);
            this.selectionView.setVisible(true);
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
