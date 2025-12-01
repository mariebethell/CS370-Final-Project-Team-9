package src.DBAdapter;

import src.model.Gym;
import src.DBAdapter.Gym_Access_IF;

import java.util.List;
import java.util.ArrayList;

// Database imports
import java.sql.*;

public class Gym_DAO extends DB_Connection implements Gym_Access_IF {
    public List<Gym> getAllGyms() {
        /**
         * Return list of Gym objects in alphabetical order by chain_name
         */
        List<Gym> gyms = new ArrayList<>();
        String query = "SELECT * FROM gyms ORDER BY chain_name";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Gym retreivedGym = new Gym( 
                    rs.getInt("gym_id"),
                    rs.getString("chain_name"),
                    rs.getInt("location_number"),
                    rs.getString("hours"),
                    rs.getInt("num_courts"),
                    rs.getString("address")
                );

                gyms.add(retreivedGym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gyms: " + e.getMessage());
        }
        return gyms;
    }

    public Gym getGymById(int id) {
        /**
         * Returns Gym object that has corresponding id
         */
        String query = "SELECT * FROM gyms WHERE gym_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Gym(
                    rs.getInt("gym_id"),
                    rs.getString("chain_name"),
                    rs.getInt("location_number"),
                    rs.getString("hours"),
                    rs.getInt("num_courts"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gym: " + e.getMessage());
        }
        return null;
    }
}
