package src.controller;

import src.app.MainApp;
import src.view.SchedulerView;
import src.model.Game;

import java.util.List;
import java.util.ArrayList;

public class SchedulerController {
    private SchedulerView view;
    private MainApp app;

    public SchedulerController(SchedulerView view, MainApp app) {
        this.view = view;
        this.app = app;

        Game game1 = new Game(null, null, "1:30");
        Game game2 = new Game(null, null, "2:00");

        List<Game> games = new ArrayList<>();

        games.add(game1);
        games.add(game2);

        view.addComponents(games);
    }
}
