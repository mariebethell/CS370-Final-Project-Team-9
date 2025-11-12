package src.controller;

import src.model.Game;
import src.DBAdapter.Game_DAO;

import java.util.List;
import java.util.ArrayList;

public class SchedulerController {
    Game_DAO game_dao;

    public SchedulerController() {
        // TEST CODE
        game_dao = new Game_DAO();

        Game new_game = new Game(1, 2, "3:30");

        game_dao.createGame(new_game);

        List<Game> games = game_dao.getAllGamesFromGym(1);

        for (Game game : games) {
            System.out.println(game.getTime());
        }

        game_dao.deleteGame(1);
    }
}
