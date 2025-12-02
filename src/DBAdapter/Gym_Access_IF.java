package src.DBAdapter;

import src.model.Gym;
import java.util.List;

public interface Gym_Access_IF {
    public List<Gym> getAllGyms();
    public Gym getGymById(int id);
}
