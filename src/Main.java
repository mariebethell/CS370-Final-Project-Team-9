package src;

import javax.swing.SwingUtilities;
import src.view.*;
// import src.model.*;
import src.controller.*;

public class Main {
    public static void main(String[] args) {
        // inovokeLater is a Swing method that makes sure threading is handled properly
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            GymSelectionView selectionView = new GymSelectionView();
            SchedulerView schedulerView= new SchedulerView();

            AppController controller = new AppController(loginView, selectionView, schedulerView);
            controller.start();
        });
    }
}