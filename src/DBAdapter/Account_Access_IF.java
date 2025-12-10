/**
 * Account Access Interface class. Methods here are implemented by Account_DAO.
 */

package src.DBAdapter;

import src.model.Account;
import java.util.List;

public interface Account_Access_IF {
    // Create
    public void addAccount(Account account);

    // Read
    public Account getAccountById(int id);
    public Account getAccountByEmail(String email);

    // Update
    public boolean updateAccount(Account account);

    // Delete
    public void deleteAccount(int id);
    public void deleteAccountByEmail(String email);

    // Validate credentials, does this username exist and does the password 
    // match what's stored in the DB 
    public boolean validateCredentials(String username, String password);
}
