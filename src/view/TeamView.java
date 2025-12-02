package src.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Team;
import src.model.Account;

import src.DBAdapter.Account_DAO;

public class TeamView extends JDialog {

    private JTextField[] playerFields = new JTextField[5];
    private JButton okButton;
    private JButton cancelButton;
    private JLabel teamManagerLabel;
    private JLabel createOrJoinTeamLabel;

    private boolean confirmed = false;
    private Team team;
    private Account_DAO account_dao;

    private Account current_user;

    public TeamView(JFrame parent, Team team, Account current_user) {
        super(parent, "Edit Team", true);
        this.team = team;
        this.current_user = current_user;

        account_dao = new Account_DAO();

        setLayout(new BorderLayout());

        add(buildLabelPanel(), BorderLayout.NORTH);
        add(buildFormPanel(team), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    /**
     *  LABEL PANEL: Team Manager and Join/Create Team labels
     */

    private JPanel buildLabelPanel() {
        JPanel panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        createOrJoinTeamLabel = new JLabel();
        
        if (team.getPlayers().size() == 0) {
            createOrJoinTeamLabel.setText("Create New Team");
        }
        else {
            createOrJoinTeamLabel.setText("Join Team");
        }

        Border create_or_join_padding = new EmptyBorder(10, 10, 0, 10);
        createOrJoinTeamLabel.setHorizontalAlignment(JLabel.CENTER);
        createOrJoinTeamLabel.setBorder(create_or_join_padding);

        panel.add(createOrJoinTeamLabel);

        // Temporary variables for extracting team manager name and email
        int teamManagerId = team.getTeamManagerId();
        Account teamManagerAccount = account_dao.getAccountById(teamManagerId);
        String teamManagerName = teamManagerAccount.getName();
        String teamManagerEmail = teamManagerAccount.getEmail();

        teamManagerLabel = new JLabel("Team Manager: " + teamManagerName + " (" + teamManagerEmail + ")");
        teamManagerLabel.setHorizontalAlignment(JLabel.CENTER); // Center the manager label
        
        Border manager_padding = new EmptyBorder(10, 10, 10, 10);
        teamManagerLabel.setBorder(manager_padding);

        panel.add(teamManagerLabel);

        return panel;
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
                            ? players.get(i).getEmail()
                            : "";

            playerFields[i] = new JTextField(name, 15);
        
            // If a name already exists in the database, make it uneditable unless current user is team manager
            if (name != "" && team.getTeamManagerId() != current_user.getAccountId()) {
                playerFields[i].setEnabled(false);
            }
            
            // If current user is not team manager, disable all but one empty input box
            if (name == "" && team.getTeamManagerId() != current_user.getAccountId()) {
                if (i > players.size()) {
                    playerFields[i].setEnabled(false);
                }
            }

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

        Team newTeam = new Team(team.getTeamId(), team.getTeamNum()); // preserve team number
        newTeam.setTeamManager(team.getTeamManagerId()); // preserve team manager id
        List<Account> players = new ArrayList<>();

        for (JTextField field : playerFields) {
            String text = field.getText().trim();

            if (!text.isBlank()) {
                Account a = account_dao.getAccountByEmail(text);
                if (a != null) {
                    // Check for player uniqueness to prevent duplicates
                    boolean unique = true;
                    for (Account player : players) {
                        if (a.getAccountId() == player.getAccountId()) {
                            unique = false;
                        }
                    }

                    if (unique) {
                        players.add(a);
                    }
                    else {
                        System.out.println(text + " was already in the team");
                    }
                }
                else {
                    System.out.println(text + " was not found in the Account database");
                }
            }
        }

        newTeam.setTeamMembers(players);
        return newTeam;
    }
}
