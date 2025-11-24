package src.DBAdapter;

import src.model.Team;
import src.model.Account;
import src.DBAdapter.Account_DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Team_DAO extends DB_Connection implements Team_Access_IF {

    private static final int MAX_PLAYERS = 5;
    private Account_DAO account_dao = new Account_DAO();

    @Override
    public void createTeam(Team team) {
        String query = "INSERT INTO teams (team_num, player1_id, player2_id, player3_id, player4_id, player5_id) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, team.teamNum);

            List<Account> players = team.getPlayers();

            for (int i = 0; i < MAX_PLAYERS; i++) {
                if (i < players.size()) {
                    stmt.setInt(i + 2, players.get(i).getAccountId());
                    System.out.println("added " + players.get(i).getAccountId() + " to the database");
                } else {
                    stmt.setNull(i + 2, Types.INTEGER);
                }
            }

            stmt.executeUpdate();

            // Assign generated team ID back to the Team object
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                team.teamId = rs.getInt(1);
                System.out.println(team.teamId);
            }
            

        } catch (SQLException e) {
            System.out.println("Error creating team: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeam(int teamId) {
        String query = "DELETE FROM teams WHERE team_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, teamId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting team: " + e.getMessage());
        }
    }

    @Override
    public void updateTeam(Team team) {
        String query = "UPDATE teams SET player1_id = ?, player2_id = ?, player3_id = ?, "
                     + "player4_id = ?, player5_id = ? WHERE team_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            List<Account> players = team.getPlayers();

            for (int i = 0; i < MAX_PLAYERS; i++) {
                if (i < players.size()) {
                    stmt.setInt(i + 1, players.get(i).getAccountId());
                } else {
                    stmt.setNull(i + 1, Types.INTEGER);
                }
            }

            stmt.setInt(6, team.teamId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating team: " + e.getMessage());
        }
    }

    @Override
    public Team getTeamById(int teamId) {
        String query = "SELECT * FROM teams WHERE team_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, teamId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int teamNum = rs.getInt("team_num");
                Team team = new Team(teamId, teamNum);
                List<Account> players = new ArrayList<>();

                for (int i = 1; i <= MAX_PLAYERS; i++) {
                    int playerId = rs.getInt("player" + i + "_id");
                    if (!rs.wasNull()) {
                        players.add(account_dao.getAccountById(playerId));
                    }
                }

                team.setTeamMembers(players);
                return team;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving team: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        String query = "SELECT * FROM teams";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int teamId = rs.getInt("team_id");
                Team team = new Team(teamId);
                List<Account> players = new ArrayList<>();

                for (int i = 1; i <= MAX_PLAYERS; i++) {
                    int playerId = rs.getInt("player" + i + "_id");
                    if (!rs.wasNull()) {
                        players.add(account_dao.getAccountById(playerId));
                    }
                }

                team.setTeamMembers(players);
                teams.add(team);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving teams: " + e.getMessage());
        }

        return teams;
    }
}
