package src.controller;

import src.app.MainApp;
import src.view.SchedulerView;
import src.view.SingleGameView;
import src.view.TeamView;

import src.model.Game;
import src.model.Team;
import src.DBAdapter.Game_DAO;

import java.util.List;
import java.util.ArrayList;

public class SchedulerController {
    private SchedulerView view;
    private MainApp app;
    private TeamView teamView;

    public SchedulerController(SchedulerView view, MainApp app) {
        this.view = view;
        this.app = app;

        Game game1 = new Game(null, null, "1:30");
        Game game2 = new Game(null, null, "2:00");

        // Test data for now, but we will change this to game_dao.getAllGamesFromGym(gymId)
        List<Game> games = new ArrayList<>();

        games.add(game1);
        games.add(game2);

        view.addComponents(games);

        initController();
    }

    private void initController() {
        for (SingleGameView game_view : this.view.getListOfGameViews()) {
            game_view.getTeam1Button().addActionListener(e -> constructTeamView(game_view.getTeam1()));
            game_view.getTeam2Button().addActionListener(e -> constructTeamView(game_view.getTeam2()));
        }
    }

    private TeamView constructTeamView(Team team) {
        teamView = new TeamView(team);
        System.out.println("New team view created");
        teamView.setVisible(true);
        return teamView;
    }
}
