/**
 * Game Access Interface class. Methods here are implemented by Game_DAO.
 */

package src.DBAdapter;

import src.model.Game;
import java.util.List;

public interface Game_Access_IF {
    // Create
    public void createGame(Game game);

    // Read
    public List<Game> getAllGamesFromGym(int gymId);

    // Update

    public boolean addTeamToGame(int gameId, int teamId, int teamNum);
    // Delete
    public void deleteGame(int gameId);
    public void deleteAllGamesFromGym(int gym_id);
}
