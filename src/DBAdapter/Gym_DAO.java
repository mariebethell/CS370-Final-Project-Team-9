package src.DBAdapter;

import src.model.Gym;
import src.DBAdapter.Gym_Access_IF;

import java.util.List;
import java.util.ArrayList;


// Database imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                    rs.getString("chain_name"),
                    rs.getInt("location_number"),
                    rs.getString("hours"),
                    rs.getInt("num_courts")
                );

                gyms.add(retreivedGym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gyms: " + e.getMessage());
        }
        return gyms;
    }
}
