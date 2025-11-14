package src.view;

import javax.swing.*;

import src.model.Team;

public class TeamView extends JFrame {
    public TeamView(Team team) {
        // Frame settings
        setTitle("Team View");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        if (team == null) {

        }
    }

    public void addComponents() {

    }
}
