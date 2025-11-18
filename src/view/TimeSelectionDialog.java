package src.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSelectionDialog extends JDialog {
    private JComboBox<String> timeBox;
    private String selectedTime = null;

    public TimeSelectionDialog(JFrame parent) {
        super(parent, "Select Game Time", true); // true = modal

        
        String[] times = { "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM" };
        timeBox = new JComboBox<>(times);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            selectedTime = (String) timeBox.getSelectedItem();
            dispose();
        });

        setLayout(new FlowLayout());
        add(new JLabel("Choose a time:"));
        add(timeBox);
        add(ok);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public LocalDateTime getSelectedTime() {
        DateTimeFormatter timeFormatter =
        DateTimeFormatter.ofPattern("h:mm a");

        LocalTime time = LocalTime.parse(selectedTime, timeFormatter);

        // Combine selected time with today's date
        LocalDate today = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.of(today, time);

        return dateTime;
    }
}
