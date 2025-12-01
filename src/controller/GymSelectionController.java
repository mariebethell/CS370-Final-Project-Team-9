package src.controller;

import src.view.GymSelectionView;
import src.model.Gym;
import src.app.MainApp;
import src.DBAdapter.Gym_DAO;

import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class GymSelectionController {
    private GymSelectionView gymSelectionView;
    private MainApp app;
    private Gym_DAO gym_dao;

    public GymSelectionController(GymSelectionView view, MainApp app) {
        this.gymSelectionView = view;
        this.app = app;

        this.gym_dao = new Gym_DAO();

        // Get list of all gyms from the database, returned as a list of Gym objects
        List<Gym> gyms = new ArrayList<>();
        gyms = gym_dao.getAllGyms();
    
        // Send gyms to GymSelectionView for display
        this.gymSelectionView.addComponents(gyms);
        
        initController();
    }

    private void initController() {
        this.gymSelectionView.getSelectionButton().addActionListener(e -> handleGymSelection());
    }

    private void handleGymSelection() {
        // Get Gym ID from view
        int currentGymId = this.gymSelectionView.getSelectedGymId();

        // Get Gym with that id from database
        Gym currentGym = gym_dao.getGymById(currentGymId);

        app.setCurrentGym(currentGym);

        // change view to SchedulerController
        app.showSchedulerView();
    }
}
