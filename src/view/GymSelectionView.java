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

    private DefaultTableModel model;
    private JTable table;
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
        String[] columnNames = {"ID", "Gym", "Address"};

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        for (Gym gym : gyms) {
            // Create an Object array for the current row's data
            Object[] rowData = new Object[3];
            rowData[0] = gym.getId();
            rowData[1] = gym.getChain();
            rowData[2] = gym.getAddress();
            model.addRow(rowData);
        }

        table = new JTable(model);

        // Hide ID column (user does not need to see internal DB ids)
        table.removeColumn(table.getColumnModel().getColumn(0));

        // Create a JScrollPane for the JTable
        gymListPane = new JScrollPane(table);

        // Add list to the center so it takes most of the space
        add(gymListPane, BorderLayout.CENTER);

        // Add the button to the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public int getSelectedGymId() {
        int selectedRowIndex = table.getSelectedRow();

        Object selectedValue = null;
        if (selectedRowIndex != -1) { // Check if a row is actually selected
            selectedValue = table.getModel().getValueAt(selectedRowIndex, 0);
            // You can cast selectedValue to the appropriate type (e.g., String)
            System.out.println("Selected value: " + selectedValue);
        }

        return (Integer) selectedValue;
    }

}