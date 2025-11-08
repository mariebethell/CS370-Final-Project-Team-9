package src.view;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;

public class GymSelectionView extends JFrame{
    private JButton selectButton = new JButton("Select");

    public JList<String> gymList;
    public JScrollPane gymListPane;

    public GymSelectionView() {
        setTitle("Gym Selection");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    public JButton getSelectionButton() {
        return selectButton;
    }

    public void addComponents(DefaultListModel<String> gyms) {
        // Create the list and scroll pane
        gymList = new JList<>(gyms);
        gymListPane = new JScrollPane(gymList);

        // Add list to the center so it takes most of the space
        add(gymListPane, BorderLayout.CENTER);

        // Add the button to the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}