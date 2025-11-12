package src.model;
public class Game {
    Team team1 =  null;
    Team team2 = null;

    int team1Id;
    int team2Id;
    int gymId;

    String time;
    boolean full = false;

    public Game(Team team1, Team team2, String time) {
        this.team1 = team1;
        this.team2 = team2;
        this.time = time;
    }

    public Game(int team1Id, int team2Id, String time) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.time = time;
    }

    public int getGymId() {
        return gymId;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public String getTime() {
        return time;
    }

    public void addTeam(Team team, int teamNum) {
        if(teamNum == 1) {
            this.team1 = team;
        }
        else{
            this.team2 = team;
        }
    }//this might not be the best way to do this or the right spot
    //team that creats game is team1 and can make changes

    public void removeTeam(int teamNum) {
        if(teamNum == 1) {
            this.team1 = null;
        }
        else{
            this.team2 = null;
        }
    }

    public boolean gameFull() {
        return team1.teamFull() && team2.teamFull();
    }

}
