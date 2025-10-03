// Test program for gym location scroll panel
// 10/3/2025

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create the top-level frame
        JFrame frame = new JFrame("PickMeUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Create an instance of GymLocationPanel
        GymLocationPanel gymLocationPanel = new GymLocationPanel();

        // Add the panel to the frame
        frame.add(gymLocationPanel, BorderLayout.NORTH);

        // Make the frame visible
        frame.setVisible(true);
    }
}