package src.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Game {
    private Team team1 =  null;
    private Team team2 = null;

    public int team1Id;
    public int team2Id;
    public int gymId;
    public int gameId;

    private LocalDateTime date_time;
    private boolean full = false;

    // Constructors
    public Game(int gymId, Team team1, Team team2, LocalDateTime time) {
        this.gymId = gymId;
        this.team1 = team1;
        this.team2 = team2;
        this.date_time = time;
    }

    public Game(int gameId, int gymId, Team team1, Team team2, LocalDateTime time) {
        this.gameId = gameId;
        this.gymId = gymId;
        this.team1 = team1;
        this.team2 = team2;
        this.date_time = time;
    }

    public Game(int gymId, int team1Id, int team2Id, LocalDateTime time) {
        this.gymId = gymId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.date_time = time;
    }

    public Game(int gameId, int gymId, int team1Id, int team2Id, LocalDateTime time) {
        this.gameId = gameId;
        this.gymId = gymId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.date_time = time;
    }

    public int getGymId() {
        return gymId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public LocalTime get_time() {
        return date_time.toLocalTime();
    }

    public LocalDateTime get_date_time() {
        return date_time;
    }

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
