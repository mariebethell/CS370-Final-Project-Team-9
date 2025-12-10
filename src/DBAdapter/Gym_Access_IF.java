/**
 * Gym Access Interface class. Methods here are implemented by Gym_DAO.
 * Only read methods are needed for now, but other CRUD methods could be added in the future.
 */
package src.DBAdapter;

import src.model.Gym;
import java.util.List;

public interface Gym_Access_IF {
    // Read
    public List<Gym> getAllGyms();
    public Gym getGymById(int id);
}
