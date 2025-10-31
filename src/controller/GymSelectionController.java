package src.controller;

import src.view.GymSelectionView;
import src.app.MainApp;

public class GymSelectionController {
    private GymSelectionView gymSelectionView;
    private MainApp app;

    public GymSelectionController(GymSelectionView view, MainApp app, String[] gymList) {
        this.gymSelectionView = view;
        this.app = app;

        this.gymSelectionView.addComponents(gymList);

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
