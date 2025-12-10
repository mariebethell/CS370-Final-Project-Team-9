/**
 * The MainApp class is the entry point of the application. It initializes the 
 * views and controllers, as well as keeps track of session information like current
 * user and current gym.
 */

package src.app;

import javax.swing.SwingUtilities;
import src.view.*;
import src.controller.*;
import src.model.*;

public class MainApp {            
    
    private LoginView loginView;
    private GymSelectionView selectionView;
    private SchedulerView schedulerView;

    private LoginController loginController;
    private GymSelectionController gymSelectionController;
    private SchedulerController schedulerController;

    // MainApp keeps a reference to the current logged in user
    private Account currentUser;

    // MainApp also keeps a reference to the current gym selected by the user
    private Gym currentGym;

    public MainApp() {
        loginView = new LoginView();
        selectionView = new GymSelectionView();
        schedulerView= new SchedulerView();

        loginController = new LoginController(loginView, this);
        gymSelectionController = new GymSelectionController(selectionView, this);
        schedulerController = new SchedulerController(schedulerView, this);

        // Apps starts at the login page
        showLoginView();
    }

    /***************************************************
     * Methods for adjusting view visibility (show/hide)
     */
    public void showLoginView() {
        // Show login page.
        hideAll();
        loginView.setVisible(true);
    }

    public void showSelectionView() {
        // Show gym selection page.
        hideAll();
        selectionView.setVisible(true);
    }

    public void showSchedulerView() {
        // Show Scheduler View (game page). View is refreshed before display.
        hideAll();
        schedulerController.refreshSchedulerView();
        schedulerView.setVisible(true);
    }

    public void hideAll() {
        // Hide all views.
        loginView.setVisible(false);
        selectionView.setVisible(false);
        schedulerView.setVisible(false);
    }

    /*******************************************************
     * Getters and setters for session specific information 
     */
    public void setCurrentGym(Gym gym) {
        currentGym = gym;
    }

    public Gym getCurrentGym() {
        return currentGym;
    }

    public void setCurrentUser(Account user) {
        currentUser = user;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        // Main function, entry point for the application.
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}