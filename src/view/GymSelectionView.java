package src.view;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymSelectionView extends JFrame{
    private JButton selectButton = new JButton("Select");

    public JList<String> gymList;
    public JScrollPane gymListPane;

    // Create a new JPanel with a GridLayout
    JPanel gridPanel = new JPanel(new GridLayout(2, 1));

    public GymSelectionView() {
        setTitle("Gym Selection");
        setSize(360, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // Use BorderLayout instead of BoxLayout
        setLocationRelativeTo(null);
    }

    public JButton getSelectionButton() {
        return selectButton;
    }

    public void addComponents(String[] gyms) {
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