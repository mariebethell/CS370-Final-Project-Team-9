package src.DBAdapter;

import java.util.List;
import java.util.ArrayList;

import src.model.Game;
import src.model.Gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Game_DAO extends DB_Connection implements Game_Access_IF {
    public void createGame(Game game) {
        String query = "INSERT INTO games (gym_id, team1_id, game_time) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, game.getGymId());
            stmt.setInt(2, game.getTeam1Id());
            stmt.setString(3, game.getTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating game: " + e.getMessage());
        }
    }

    public void deleteGame(int gameId) {
        String query = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting game: " + e.getMessage());
        }
    }

    public boolean addTeamToGame(int gameId, int teamId) {
        String query = "UPDATE games SET team2_id = ? WHERE game_id = ?";
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
    
    public List<Game> getAllGamesFromGym(int gymId) {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM games WHERE gym_id = ? ORDER BY game_time";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Game retreivedGame = new Game( 
                    rs.getInt("team1_id"),
                    rs.getInt("team2_id"),
                    rs.getString("time")
                );
                games.add(retreivedGame);
            }
            
        } catch (SQLException e) {
            System.out.println("Error retrieving games: " + e.getMessage());
        }
        return games;
    }
}
