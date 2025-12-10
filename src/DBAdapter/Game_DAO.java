/**
 * Implements methods of the Game_Access_IF. Inherits DB_Connection constructor and methods.
 */
package src.DBAdapter;

import java.util.List;
import java.util.ArrayList;

import src.model.Game;
import src.model.Gym;

import java.sql.*;
import java.time.LocalDateTime;

public class Game_DAO extends DB_Connection implements Game_Access_IF {
    // Create
    public void createGame(Game game) {
        String query = "INSERT INTO games (gym_id, game_time) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, game.getGymId());
            stmt.setTimestamp(2, Timestamp.valueOf(game.get_date_time()));
            stmt.executeUpdate();

            // Assign generated game ID back to the Game object
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                game.gameId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error creating game: " + e.getMessage());
        }
    }

    // Read
    public List<Game> getAllGamesFromGym(int gymId) {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM games WHERE gym_id = ? ORDER BY game_time";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, gymId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int team1_id = rs.getInt("team1_id");
                int team2_id = rs.getInt("team2_id");
                LocalDateTime datetime = rs.getTimestamp("game_time").toLocalDateTime();
                int game_id = rs.getInt("game_id");

                Game retrievedGame = new Game(game_id, gymId, team1_id, team2_id, datetime);
                games.add(retrievedGame);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving games: " + e.getMessage());
        }
        return games;
    }

    // Update
    public boolean addTeamToGame(int gameId, int teamId, int teamNum) {
        String query;

        if (teamNum == 1) {
            query = "UPDATE games SET team1_id = ? WHERE game_id = ?";
        } else if (teamNum == 2) {
            query = "UPDATE games SET team2_id = ? WHERE game_id = ?";
        } else {
            System.out.println("Invalid team number: " + teamNum);
            return false;
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, teamId);
            stmt.setInt(2, gameId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error adding team to game: " + e.getMessage());
            return false;
        }
    }

    // Delete
    public void deleteGame(int gameId) {
        String query = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting game: " + e.getMessage());
        }
    }

    public void deleteAllGamesFromGym(int gym_id) {
        String query = "DELETE FROM games WHERE gym_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gym_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting game: " + e.getMessage());
        }
    }
}
