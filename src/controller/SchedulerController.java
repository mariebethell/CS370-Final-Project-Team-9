package src.controller;

import src.app.MainApp;
import src.view.SchedulerView;
import src.view.SingleGameView;
import src.view.TeamView;
import src.view.TimeSelectionDialog;

import src.model.Game;
import src.model.Team;
import src.DBAdapter.Game_DAO;
import src.DBAdapter.Team_DAO;

import java.time.LocalDateTime;
import java.util.List;

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

    // -------------------------------------------------------------------------
    // INITIALIZATION
    // -------------------------------------------------------------------------
    private void initController() {

        view.getCreateGameButton().addActionListener(e -> {
            TimeSelectionDialog dialog = new TimeSelectionDialog(view);
            dialog.setVisible(true);

            LocalDateTime time = dialog.getSelectedTime();
            if (time != null) {
                Game game = new Game(1, null, null, time);
                game_dao.createGame(game);
                refreshSchedulerView();
            }
        });

        // Attach listeners to game buttons
        wireGameButtons();
    }

    // -------------------------------------------------------------------------
    // REFRESH VIEW + REWIRE BUTTONS
    // -------------------------------------------------------------------------
    private void refreshSchedulerView() {
        List<Game> games = game_dao.getAllGamesFromGym(1);
        view.refreshView(games);
        wireGameButtons();
    }

    // -------------------------------------------------------------------------
    // WIRE TEAM BUTTONS
    // -------------------------------------------------------------------------
    private void wireGameButtons() {
        for (SingleGameView gv : view.getListOfGameViews()) {

            gv.getTeam1Button().addActionListener(e -> {
                Team team = gv.getTeam1();
                if (team == null) team = new Team(1);
                openTeamEditor(gv, team, 1);
            });

            gv.getTeam2Button().addActionListener(e -> {
                Team team = gv.getTeam2();
                if (team == null) team = new Team(2);
                openTeamEditor(gv, team, 2);
            });
        }
    }

    // -------------------------------------------------------------------------
    // OPEN TEAM EDITOR (TeamView)
    // -------------------------------------------------------------------------
    private void openTeamEditor(SingleGameView gameView, Team team, int teamNum) {

        TeamView editor = new TeamView(view, team);
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
                gameView.setTeam1(updatedTeam);
                game_dao.addTeamToGame(gameView.getGameId(), updatedTeam.teamId, updatedTeam.teamNum);

            } else {
                gameView.setTeam2(updatedTeam);
                game_dao.addTeamToGame(gameView.getGameId(), updatedTeam.teamId, updatedTeam.teamNum);
            }

            // Rebuild scheduler UI
            refreshSchedulerView();
        }
    }
}
