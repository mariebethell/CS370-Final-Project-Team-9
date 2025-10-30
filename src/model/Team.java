package src.model;
public class Team{
    int teamNum; //determined based on whether or not they create the game
    Account[] players; //array of accounts
    private int MAXCOUNT = 5;

    public void addPlayer(Account newPlayer){
        if(players.length < MAXCOUNT){
            players[players.length] = newPlayer;
        }
        else{
            System.out.println("Team is full, cannot add more players.");
            //this turns into an error window
        }
    }
    public Team(int teamNum){
        this.teamNum = teamNum;
        players = new Account[MAXCOUNT];
    }

    public boolean teamFull(){
        return players.length == MAXCOUNT;
    }
}


