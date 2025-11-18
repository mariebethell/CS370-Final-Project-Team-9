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

    private Team team1;
    private Team team2;

    public Game game;

    public SingleGameView(Game game) {
        this.game = game;

        this.team1 = game.getTeam1();
        this.team2 = game.getTeam2();

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

        gameIdLabel = new JLabel("Game " + game.gameId);
        gameIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameTimeLabel = new JLabel(formatTime(game));
        gameTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameInfoPanel.add(Box.createVerticalStrut(20));
        gameInfoPanel.add(gameIdLabel);
        gameInfoPanel.add(gameTimeLabel);

        contentPanel.add(gameInfoPanel);

        // ----- Team Buttons -----
        team1Button = createTeamButton(team1, "Team 1");
        team2Button = createTeamButton(team2, "Team 2");

        contentPanel.add(team1Button);
        contentPanel.add(team2Button);

        // Add to main view
        add(contentPanel, BorderLayout.CENTER);
    }

    // ---------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------

    private JButton createTeamButton(Team team, String defaultText) {
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

    public JButton getTeam1Button() { return team1Button; }
    public JButton getTeam2Button() { return team2Button; }

    public Team getTeam1() { return team1; }
    public Team getTeam2() { return team2; }

    public int getGameId() {
        return game.gameId;
    }

    // Setters
    public void setTeam1(Team team) {
        team1 = team;
    }

    public void setTeam2(Team team) {
        team2 = team;
    }
}
