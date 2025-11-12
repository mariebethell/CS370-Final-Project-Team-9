package src.DBAdapter;

import src.model.Gym;
import java.util.List;

public interface Gym_Access_IF extends DB_Connection_IF {
    // used if we wanted to implement searching by chain name
    // public Gym getGymByChainName (String chainName); 

    public List<Gym> getAllGyms();
}
