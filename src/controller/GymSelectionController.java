/**
 * The GymSelectionController is responsible for updating the GymSelectionView and
 * retrieving/inserting gym data into the database.
 */

package src.controller;

import src.app.MainApp;
import src.view.GymSelectionView;
import src.model.Gym;
import src.DBAdapter.Gym_DAO;

import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class GymSelectionController {
    private GymSelectionView gymSelectionView;
    private MainApp app;
    private Gym_DAO gym_dao;

    public GymSelectionController(GymSelectionView view, MainApp app) {
        /**
         * Constructor - intializes instance variables and updates view with gyms from database.
         * This ensures that the GymSelectionView has valid gyms to display as soon as 
         * the gymSelectionController is created.
         */
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
        /**
         * Adds action listeners to buttons
         */
        this.gymSelectionView.getSelectionButton().addActionListener(e -> handleGymSelection());
    }

    private void handleGymSelection() {
        /**
         * 1. Retrieves id of gym selected by the user.
         * 2. Retrieves gym from database.
         * 3. Updates MainApp's current gym reference with selected gym.
         */

        // Get Gym ID from view
        int currentGymId = this.gymSelectionView.getSelectedGymId();

        // Get Gym with that id from database
        Gym currentGym = gym_dao.getGymById(currentGymId);

        // Set MainApp's current gym to the selected gym
        app.setCurrentGym(currentGym);

        // Change view to SchedulerController
        app.showSchedulerView();
    }
}
