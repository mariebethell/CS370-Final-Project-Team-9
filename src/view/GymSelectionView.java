package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.util.List;
import java.util.ArrayList;

import src.model.Gym;
import javax.swing.table.*;

public class GymSelectionView extends JFrame{
    private JButton selectButton = new JButton("Select");

    private JList<String> gymList;
    private JScrollPane gymListPane;

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

    public void addComponents(List<Gym> gyms) {
        // Use JTable to display gym names and addresses
        String[] columnNames = {"Gym", "Address"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        for (Gym gym : gyms) {
            // Create an Object array for the current row's data
            Object[] rowData = new Object[2];
            rowData[0] = gym.getChain();
            rowData[1] = gym.getAddress();
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        // Create a JScrollPane for the JTable
        gymListPane = new JScrollPane(table);

        // Add list to the center so it takes most of the space
        add(gymListPane, BorderLayout.CENTER);

        // Add the button to the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}