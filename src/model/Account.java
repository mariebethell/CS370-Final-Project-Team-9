/**
 * Model class for Account.
 * Also used as DTO, hence second constuctor that intializes ID field which is generated in database.
 */

package src.model;

public class Account {
    private String name;
    private String email;
    private String password;
    private int accountId;

    public Account(String name, String email, String password) {
        /**
         * Constructor - Used to create new Account object with name, email, and password.
         */
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account(int accountId, String name, String email, String password) {
        /**
         * Constructor - Used to create new Account object to store information retrieved from database.
         */
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }   

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
    
    // No setter for AccountId since this is assigned by the database
    public int getAccountId() {
        return accountId;
    }

    // Insecure, but Account_DAO needs to be able to get password from Account object when inserting into table
    public String getPassword() {
        return password;
    }
}
