package src.DBAdapter;

import src.model.Game;
import java.util.List;

import java.time.LocalDate;

public interface Game_Access_IF extends DB_Connection_IF {
    public void createGame(Game game);
    public void deleteGame(int gameId);
    public boolean addTeamToGame(int gameId, int teamId);
    public List<Game> getAllGamesFromGym(int gymId);
}
