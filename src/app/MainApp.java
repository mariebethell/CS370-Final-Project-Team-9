package src.app;

import javax.swing.SwingUtilities;
import src.view.*;
import src.controller.*;

public class MainApp {            
    
    private LoginView loginView;
    private GymSelectionView selectionView;
    private SchedulerView schedulerView;

    private LoginController loginController;
    private GymSelectionController gymSelectionController;
    private SchedulerController schedulerController;

    public MainApp() {
        loginView = new LoginView();
        selectionView = new GymSelectionView();
        schedulerView= new SchedulerView();

        loginController = new LoginController(loginView, this);

        // String[] gymNames = {"Gold's Gym", "24 Hour Fitness", "Planet Fitness", "LA Fitness", "Anytime Fitness", "Equinox", "Crunch Fitness", "YMCA", "Snap Fitness", "Life Time Fitness"};
        
        gymSelectionController = new GymSelectionController(selectionView, this);
        schedulerController = new SchedulerController();

        showLoginView();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}