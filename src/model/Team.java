package src.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public int teamNum;
    public int teamId = 0;
    private List<Account> players;
    private final int MAXCOUNT = 5;

    public Team(int teamNum) {
        this.teamNum = teamNum;
        this.players = new ArrayList<>();
    }

    public Team(int teamId, int teamNum) {
        this.teamNum = teamNum;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Account newPlayer) {
        if (players.size() < MAXCOUNT) {
            players.add(newPlayer);
        } else {
            System.out.println("Team is full, cannot add more players.");
            // later replace with error dialog
        }
    }

    public boolean teamFull() {
        return players.size() >= MAXCOUNT;
    }

    public void setTeamMembers(List<Account> newPlayers) {
        players.clear();
        for (int i = 0; i < Math.min(MAXCOUNT, newPlayers.size()); i++) {
            players.add(newPlayers.get(i));
        }
    }

    public List<Account> getPlayers() {
        return new ArrayList<>(players); 
    }

    public int getTeamNum() {
        return teamNum;
    }

    public int getTeamId() {
        return teamId;
    }

}

