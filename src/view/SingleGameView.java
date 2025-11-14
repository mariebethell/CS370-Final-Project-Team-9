package src.view;

import java.awt.*;
import javax.swing.*;

import src.model.Game;
import src.model.Team;

public class SingleGameView extends JPanel {
    private JButton team1Button;
    private JButton team2Button;
    private JPanel game_info_panel;
    private JPanel flow_panel;
    private JLabel gameTime;
    private JLabel gameId;
    private Team team1;
    private Team team2;

    // Constructor
    public SingleGameView(Game game) {
        // Get teams
        team1 = game.getTeam1();
        team2 = game.getTeam2();

        flow_panel = new JPanel();
        flow_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

        Dimension fixedSize = new Dimension(300,120);
        flow_panel.setPreferredSize(fixedSize); // to fit 4 games per screen
        flow_panel.setMinimumSize(fixedSize);
        flow_panel.setMaximumSize(fixedSize);

        flow_panel.setBackground(Color.LIGHT_GRAY);
        
        setLayout(new BorderLayout());
        setPreferredSize(fixedSize);
        setMinimumSize(fixedSize);
        setMaximumSize(fixedSize);

        game_info_panel = new JPanel();
        game_info_panel.setLayout(new BoxLayout(game_info_panel, BoxLayout.Y_AXIS));
        game_info_panel.setPreferredSize(new Dimension(80, 80));

        gameId = new JLabel("Game 1");
        gameId.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameId.setVerticalAlignment(JLabel.CENTER);
        gameTime = new JLabel("3:30");
        gameTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTime.setVerticalAlignment(JLabel.CENTER);

        game_info_panel.add(Box.createRigidArea(new Dimension(20, 20)));
        game_info_panel.add(gameId);
        game_info_panel.add(gameTime);

        flow_panel.add(game_info_panel);

        team1Button = new JButton("Team 1"){
            { 
                setPreferredSize(new Dimension(80, 80)); 
            }
        };
        
        flow_panel.add(team1Button);
        team2Button = new JButton("Team 2")
        {
            {
                setPreferredSize(new Dimension(80, 80));
            }
        };
        flow_panel.add(team2Button);

        add(flow_panel, BorderLayout.CENTER);
    }

    public JButton getTeam1Button() {
        return team1Button;
    }

    public JButton getTeam2Button() {
        return team2Button;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
}
