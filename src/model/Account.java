package src.model;

public class Account {
    String name;
    String email;
    String password;
    int teamNum = 0;
    int accountId;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account(int accountId, String name, String email, String password) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

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
    
    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public int getAccountId() {
        return accountId;
    }

    // Insecure, but Account_DAO needs to be able to get password from Account object when inserting into table
    // TODO: Make more secure
    public String getPassword() {
        return password;
    }
}
