package src.controller;

import src.app.MainApp;
import src.view.*;

import src.model.Game;
import src.model.Team;
import src.DBAdapter.Game_DAO;
import src.DBAdapter.Team_DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import java.awt.event.*;

public class SchedulerController {

    private final SchedulerView view;
    private final MainApp app;

    private final Game_DAO game_dao;
    private final Team_DAO team_dao;

    public SchedulerController(SchedulerView view, MainApp app) {
        this.view = view;
        this.app = app;

        this.game_dao = new Game_DAO();
        this.team_dao = new Team_DAO();

        // Initial render of scheduler
        refreshSchedulerView();

        // Set up button listeners
        initController();
    }

    private void initController() {
        view.getCreateGameButton().addActionListener(e -> {
            TimeSelectionDialog dialog = new TimeSelectionDialog(view, app.getCurrentGym());
            dialog.setVisible(true);

            LocalDateTime time = dialog.getSelectedTime();
            if (time != null) {
                Game game = new Game(app.getCurrentGym().getId(), null, null, time);
                game_dao.createGame(game);
                refreshSchedulerView();
            }
        });

        // Attach listeners to game buttons
        wireGameButtons();
    }

    public void refreshSchedulerView() {
        List<Game> games;
        
        if (app.getCurrentGym() == null) {
            games = new ArrayList<>();
        }
        else {
            games = game_dao.getAllGamesFromGym(app.getCurrentGym().getId());
        }

        view.refreshView(games);
        wireGameButtons();
    }

    private void wireGameButtons() {
        for (SingleGameView gv : view.getListOfGameViews()) {
            // Remove old listeners
        
            for (ActionListener al : gv.getTeam1Button().getActionListeners())
                gv.getTeam1Button().removeActionListener(al);

            for (ActionListener al : gv.getTeam2Button().getActionListeners())
                gv.getTeam2Button().removeActionListener(al);

            // Add listeners back to buttons
            gv.getTeam1Button().addActionListener(e -> {
                int team1Id = gv.getTeam1Id();

                Team team1 = null;
                if (team1Id == 0) {
                    team1 = new Team(1);

                    // Set the account that is currently logged in and creating the team as the team manager
                    team1.setTeamManager(app.getCurrentUser().getAccountId());
                }
                else {
                    team1 = team_dao.getTeamById(team1Id);
                }
                openTeamEditor(gv, team1, 1);
            });

            gv.getTeam2Button().addActionListener(e -> {
                int team2Id = gv.getTeam2Id();

                Team team2 = null;
                if (team2Id == 0) {
                    team2 = new Team(2);

                    // Set the account that is currently logged in and creating the team as the team manager
                    team2.setTeamManager(app.getCurrentUser().getAccountId());
                } 
                else {
                    team2 = team_dao.getTeamById(team2Id);
                }
                openTeamEditor(gv, team2, 2);
            });
        }
    }

    private void openTeamEditor(SingleGameView gameView, Team team, int teamNum) {

        TeamView editor = new TeamView(view, team, app.getCurrentUser());
        editor.setVisible(true);

        // If user pressed OK
        if (editor.isConfirmed()) {

            Team updatedTeam = editor.getTeamResult();

            // Save / update team to DB
            if (updatedTeam.teamId == 0) {
                // New team
                team_dao.createTeam(updatedTeam);
            } else {
                // Existing team updated
                team_dao.updateTeam(updatedTeam);
            }

            // Update game object
            if (teamNum == 1) {
                game_dao.addTeamToGame(gameView.getGameId(), updatedTeam.teamId, updatedTeam.teamNum);

            } else {
                game_dao.addTeamToGame(gameView.getGameId(), updatedTeam.teamId, updatedTeam.teamNum);
            }
            
            // Rebuild scheduler UI
            refreshSchedulerView();
        }
    }
}
