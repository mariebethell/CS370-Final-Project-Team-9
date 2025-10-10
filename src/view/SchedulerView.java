package src.view;

import javax.swing.*;
import java.awt.*;

public class SchedulerView extends JFrame {
    private JButton createTeamButton = new JButton("Create Team");

    public SchedulerView() {
        setTitle("Scheduler");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        
        add(createTeamButton);
    }

    public JButton getCreateTeamButton() {
        return createTeamButton;
    }
}
