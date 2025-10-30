//Account class to manage user information
public class Account {
    String name;
    String email;
    String password;
    int teamNum;

    public Account(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }   

    public void setName(String newName) {
        this.name = newName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    //private because nothing needs to change password except the user themselves
    //this only happens during createAccount anyway
    //not needed?
    private void setPassword(String newPassword) {
        this.password = newPassword;
    }   

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

}
