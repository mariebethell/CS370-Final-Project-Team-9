package src.DBAdapter;

import src.model.Team;
import java.util.List;

public interface Team_Access_IF {
    /**
     * Create a new team in the database.
     */
    void createTeam(Team team);

    /**
     * Delete a team by its ID.
     */
    void deleteTeam(int teamId);

    /**
     * Update player assignments for the given team.
     */
    void updateTeam(Team team);

    /**
     * Retrieve a team by ID.
     */
    Team getTeamById(int teamId);

    /**
     * Retrieve all teams in the database.
     */
    List<Team> getAllTeams();
}
