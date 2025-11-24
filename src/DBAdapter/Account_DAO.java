package src.DBAdapter;

import src.DBAdapter.Account_Access_IF;
import src.model.Account;

import java.util.List;
import java.util.ArrayList;

// Database imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account_DAO extends DB_Connection implements Account_Access_IF {

    // Create method
    public void addAccount(Account account) {
        String query = "INSERT INTO accounts (name, email, password_hash, team_num) VALUES (?, ?, ?, ?)";

        try 
            (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, account.getName());
                stmt.setString(2, account.getEmail());
                stmt.setString(3, account.getPassword());
                stmt.setInt(4, account.getTeamNum());

                stmt.executeUpdate();
            }
        catch (SQLException e) {
            System.err.println("Account creation error: " + e.getMessage());
        }
    }

    // Read methods
    public Account getAccountById(int id) {
        String query = "SELECT * FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    ""
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }
        return null;
    }

    public Account getAccountByEmail(String email) {
        String query = "SELECT * FROM accounts WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    ""
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }
        return null;
    }

    // We can implement this if we ever want to verify that database is actually being updated correctly for example
    /*
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();

        return accounts;
    }
    */

    // Update
    public boolean updateAccount(Account account) {
        String query = "UPDATE accounts SET name = ?, email = ?, password_hash = ?, team_num = ? WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getPassword());
            stmt.setInt(4, account.getTeamNum());
            stmt.setString(5, account.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Account update error: " + e.getMessage());
            return false;
        }
    }

    // Delete
    public void deleteAccount(int id) {
        String query = "DELETE FROM accounts WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Account deletion error: " + e.getMessage());
        }
    }

    public void deleteAccountByEmail(String email) {
        String query = "DELETE FROM accounts WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Account deletion error: " + e.getMessage());
        }
    }

    // Validate credentials, does this email exist and does the password 
    // match what's stored in the DB 
    public boolean validateCredentials(String email, String password) {
        String query = "SELECT password_hash FROM accounts WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedPass = rs.getString("password_hash");
                return storedPass.equals(password);
            }
        } catch (SQLException e) {
            System.err.println("Error validating credentials:" + e.getMessage());
        } 
        return false;
    }
}
