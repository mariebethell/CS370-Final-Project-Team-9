package src.view;

import javax.swing.*;
import java.awt.*;

import src.view.SingleGameView;
import src.model.Game;

import java.util.List;
import java.util.ArrayList;

public class SchedulerView extends JFrame {
    private JButton createTeamButton = new JButton("Create Team");
    private JList gameList;
    private List<SingleGameView> game_views;
    private JPanel gamePanel;
    private JScrollPane gamePane;

    public SchedulerView() {
        setTitle("Scheduler");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    public JButton getCreateTeamButton() {
        return createTeamButton;
    }

    public void addComponents(List<Game> games) {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        
        // Add border between JPanel and top of JFrame
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10));

        game_views = new ArrayList<>();
        for (Game game : games) {
            SingleGameView game_view = new SingleGameView(game);
            gamePanel.add(game_view);
            gamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
            game_views.add(game_view);
        }

        gamePane = new JScrollPane(gamePanel);

        add(gamePane, BorderLayout.CENTER);
        add(createTeamButton, BorderLayout.SOUTH);
    }

    public List<SingleGameView> getListOfGameViews() {
        return game_views;
    }
}
