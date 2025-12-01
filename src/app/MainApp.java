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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}