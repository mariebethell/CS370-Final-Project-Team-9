package src.view;

import javax.swing.*;
import java.awt.*;

public class GymSelectionView extends JFrame{
    private JButton selectButton = new JButton("Select");

    public GymSelectionView() {
        setTitle("Gym Selection");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        add(selectButton);
    }

    public JButton getSelectionButton() {
        return selectButton;
    }
}
