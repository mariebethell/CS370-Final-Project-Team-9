/**
 * Team Access Interface class. Methods here are implemented by Team_DAO.
 */

package src.DBAdapter;

import src.model.Team;
import java.util.List;

public interface Team_Access_IF {
    // Create
    void createTeam(Team team);

    // Read
    Team getTeamById(int teamId);
    List<Team> getAllTeams();

    // Update
    void updateTeam(Team team);

    // Delete
    void deleteTeam(int teamId);
}
