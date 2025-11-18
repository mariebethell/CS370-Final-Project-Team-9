package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Team;
import src.model.Account;

public class TeamView extends JDialog {

    private JTextField[] playerFields = new JTextField[5];
    private JButton okButton;
    private JButton cancelButton;

    private boolean confirmed = false;
    private Team team;

    public TeamView(JFrame parent, Team team) {
        super(parent, "Edit Team", true);
        this.team = team;

        setLayout(new BorderLayout());
        add(buildFormPanel(team), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    /** -------------------------------------------------------------------------------------
     *  FORM PANEL: Player text boxes
     * ----------------------------------------------------------------------------------- */
    private JPanel buildFormPanel(Team team) {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(8, 8, 8, 8);
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 0;

        List<Account> players = (team == null) ? new ArrayList<>() : team.getPlayers();

        for (int i = 0; i < 5; i++) {
            // Player Label
            c.gridx = 0;
            panel.add(new JLabel("Player " + (i + 1) + ":"), c);

            // Prefill existing player name if present
            String name = (i < players.size() && players.get(i) != null)
                            ? players.get(i).getName()
                            : "";

            playerFields[i] = new JTextField(name, 15);

            c.gridx = 1;
            panel.add(playerFields[i], c);

            c.gridy++;
        }

        return panel;
    }

    /** -------------------------------------------------------------------------------------
     *  BUTTON PANEL (OK, Cancel)
     * ----------------------------------------------------------------------------------- */
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        panel.add(okButton);
        panel.add(cancelButton);

        return panel;
    }

    /** -------------------------------------------------------------------------------------
     *  RESULT ACCESS
     * ----------------------------------------------------------------------------------- */

    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Builds a new Team object from the entered names.
     * The controller should call this ONLY if isConfirmed() == true.
     */
    public Team getTeamResult() {
        if (!confirmed) return null;

        Team newTeam = new Team(team.getTeamNum()); // preserve team number
        List<Account> players = new ArrayList<>();

        for (JTextField field : playerFields) {
            String text = field.getText().trim();
            if (!text.isBlank()) {
                Account a = new Account(text, null, null);  // use your real Account constructor
                players.add(a);
            }
        }

        newTeam.setTeamMembers(players);

        return newTeam;
    }
}
