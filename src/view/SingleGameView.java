package src.view;

import java.awt.*;
import javax.swing.*;

import src.model.Game; // might take out

public class SingleGameView extends JPanel {
    private JButton team1Button;
    private JButton team2Button;
    private JLabel gameTime;
    private JLabel gameId;

    // Constructor
    public SingleGameView(Game game) {
        setLayout(new FlowLayout());

        Dimension fixedSize = new Dimension(300,120);
        setPreferredSize(fixedSize); // to fit 4 games per screen
        setMinimumSize(fixedSize);
        setMaximumSize(fixedSize);

        setBackground(Color.LIGHT_GRAY);
        gameId = new JLabel("Game 1");
        add(gameId);
        team1Button = new JButton("Team 1"){
            { 
                setPreferredSize(new Dimension(80, 80)); 
            }
        };
        
        add(team1Button);
        team2Button = new JButton("Team 2")
        {
            {
                setPreferredSize(new Dimension(80, 80));
            }
        };
        add(team2Button);
    }
}
