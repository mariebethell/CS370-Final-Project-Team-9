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

        // Extract gym names and store in a DefaultListModel so it can be passed to GymSelectionView
        // TODO: extract address as well, requires modification of Gym class to include an address attribute
       /* DefaultListModel<String> gymNames = new DefaultListModel<>();
        for (Gym gym : gyms) {
            gymNames.addElement(gym.getChain());
        } */
    
        this.gymSelectionView.addComponents(gyms);
        
        initController();
    }

    private void initController() {
        this.gymSelectionView.getSelectionButton().addActionListener(e -> handleGymSelection());
    }

    private void handleGymSelection() {
        // handle it in the future
        // change view to SchedulerController
        app.showSchedulerView();
    }
}
