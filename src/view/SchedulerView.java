package src.view;

import javax.swing.*;
import java.awt.*;

import src.view.SingleGameView;
import src.model.Game;

import java.util.List;
import java.util.ArrayList;

public class SchedulerView extends JFrame {
    private JButton createGameButton = new JButton("Create Game");
    private JButton backButton = new JButton("Back");
    private JPanel backButtonPanel;
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

        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gamePane = new JScrollPane(gamePanel);

        add(gamePane, BorderLayout.CENTER);

        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.add(backButton, BorderLayout.WEST);

        add(backButtonPanel, BorderLayout.NORTH);
        add(createGameButton, BorderLayout.SOUTH);
    }


    public JButton getCreateGameButton() {
        return createGameButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void addComponents(List<Game> games) {
        game_views = new ArrayList<>();

        for (Game game : games) {
            SingleGameView game_view = new SingleGameView(game);
            gamePanel.add(game_view);
            gamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
            game_views.add(game_view);
        }

        for (SingleGameView game_view : game_views) {
            System.out.println("Game " + game_view.getGameId() + "\nTeam 1 ID: " + game_view.getTeam1Id() + "\nTeam 2 ID: " + game_view.getTeam2Id());
        }
    }

    public List<SingleGameView> getListOfGameViews() {
        return game_views;
    }

    public void refreshView(List<Game> games) {
        gamePanel.removeAll();    // Clear the list
        addComponents(games);     // Add updated game views

        gamePanel.revalidate();
        gamePanel.repaint();
    }
}
