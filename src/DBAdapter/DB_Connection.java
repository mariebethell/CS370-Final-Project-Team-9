/**
 * Parent class for all the DAO classes. Responsible for establishing a connection with the database.
 */

package src.DBAdapter;

import java.util.List;
import java.util.ArrayList;

// Database imports
import java.sql.*;

public class DB_Connection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_scheduler"; // Use local host
    private static final String DB_USER = "mariebethell";       // Change to your MySQL username
    private static final String DB_PASSWORD = "database4CS370"; // Change to your MySQL password

    protected Connection connection;

    // Constructor that connects to database
    public DB_Connection() {
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

    // Close connection
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

}
