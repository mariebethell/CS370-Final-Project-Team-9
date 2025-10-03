import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GymLocationPanel extends JPanel {
    public GymLocationPanel() {
        // Constructor for GymLocationPanel
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Set preferred size
        this.setPreferredSize(new Dimension(300, 200));
        this.setAlignmentX(LEFT_ALIGNMENT);
        
        // Create list of gym names and add to scroll ipane
        String[] gymNames = {"Gold's Gym", "24 Hour Fitness", "Planet Fitness", "LA Fitness", "Anytime Fitness", "Equinox", "Crunch Fitness", "YMCA", "Snap Fitness", "Life Time Fitness"};
        JList<String> gymList = new JList<>(gymNames);
        JScrollPane gymListPane = new JScrollPane(gymList);
        this.add(gymListPane);

        // Get selection button
        JButton getSelectionButton = new JButton("Get selected");
        getSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + gymList.getSelectedValue());
            }
        });

        this.add(getSelectionButton);

        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
    }
}