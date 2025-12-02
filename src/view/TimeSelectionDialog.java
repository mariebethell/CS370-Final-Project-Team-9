package src.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import src.model.Gym;

public class TimeSelectionDialog extends JDialog {
    private JComboBox<String> timeBox;
    private String selectedTime = null;

    public TimeSelectionDialog(JFrame parent, Gym gym) {
        super(parent, "Select Game Time", true); // true = modal

        // Generate time slots from gym hours at 30  minute intervals
        LocalTime startTime = gym.getOpenTime();
        LocalTime endTime = gym.getCloseTime();
        
        List<String> timeSlots = generateTimeSlots(startTime, endTime, 30);

        // Convert list of times to array for combobox
        String[] times = timeSlots.toArray(String[]::new);
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

    public List<String> generateTimeSlots(LocalTime open, LocalTime close, int intervalMinutes) {
        List<String> slots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        LocalTime time = open;

        while (!time.isAfter(close.minusMinutes(intervalMinutes))) {
            slots.add(time.format(formatter));
            time = time.plusMinutes(intervalMinutes);
        }
        return slots;
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
