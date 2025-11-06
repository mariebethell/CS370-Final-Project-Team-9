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

public class Gym_DAO implements Gym_Access_IF {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_scheduler"; // Use local host for now
    private static final String DB_USER = "mariebethell";       // Change to your MySQL username
    private static final String DB_PASSWORD = "database4CS370"; // Change to your MySQL password

    private Connection connection;

    // Constructor that connects to database
    public Gym_DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    // Get connection for custom queries
    public Connection getConnection() {
        return connection;
    }

    // close connections
    public Connection closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
        return connection;
    }
    
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
