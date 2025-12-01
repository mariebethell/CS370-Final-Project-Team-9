package src.view;

import java.awt.*;
import javax.swing.*;

import src.model.Game;
import src.model.Team;

public class SingleGameView extends JPanel {

    private static final Dimension GAME_SIZE = new Dimension(300, 120);
    private static final Dimension TEAM_BUTTON_SIZE = new Dimension(80, 80);

    private JButton team1Button;
    private JButton team2Button;
    private JLabel gameTimeLabel;
    private JLabel gameIdLabel;

    public Game game;

    public SingleGameView(Game game) {
        this.game = game;

        setLayout(new BorderLayout());
        setPreferredSize(GAME_SIZE);
        setMinimumSize(GAME_SIZE);
        setMaximumSize(GAME_SIZE);

        // Main container panel
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        contentPanel.setPreferredSize(GAME_SIZE);
        contentPanel.setOpaque(true);
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // ----- Game Info Panel -----
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));
        gameInfoPanel.setOpaque(false);  
        gameInfoPanel.setPreferredSize(new Dimension(80, 80));

        gameIdLabel = new JLabel("Game " + game.getGameId());
        gameIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameTimeLabel = new JLabel(formatTime(game));
        gameTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameInfoPanel.add(Box.createVerticalStrut(20));
        gameInfoPanel.add(gameIdLabel);
        gameInfoPanel.add(gameTimeLabel);

        contentPanel.add(gameInfoPanel);

        // ----- Team Buttons -----
        team1Button = createTeamButton("Team 1");
        team2Button = createTeamButton("Team 2");

        contentPanel.add(team1Button);
        contentPanel.add(team2Button);

        // Add to main view
        add(contentPanel, BorderLayout.CENTER);
    }

    // ---------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------

    private JButton createTeamButton(String defaultText) {
        JButton button = new JButton(defaultText);
        button.setPreferredSize(TEAM_BUTTON_SIZE);
        return button;
    }

    private String formatTime(Game game) {
        if (game.get_time() == null) return "No time";
        return game.get_time().toString();
    }

    // ---------------------------------------------------------
    // Getters
    // ---------------------------------------------------------

    public JButton getTeam1Button() { 
        return team1Button; 
    }

    public JButton getTeam2Button() {
        return team2Button; 
    }

    public int getTeam1Id() { 
        return game.getTeam1Id(); 
    }

    public int getTeam2Id() { 
        return game.getTeam2Id();
    }

    public int getGameId() {
        return game.gameId;
    }
}
