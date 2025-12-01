package src.app;

import javax.swing.SwingUtilities;
import src.view.*;
import src.controller.*;
import src.model.*;

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

        showLoginView();
        // showSchedulerView();
    }

    public void showLoginView() {
        hideAll();
        loginView.setVisible(true);
    }

    public void showSelectionView() {
        hideAll();
        selectionView.setVisible(true);
    }

    public void showSchedulerView() {
        hideAll();
        schedulerController.refreshSchedulerView();
        schedulerView.setVisible(true);
    }

    public void hideAll() {
        loginView.setVisible(false);
        selectionView.setVisible(false);
        schedulerView.setVisible(false);
    }

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
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}