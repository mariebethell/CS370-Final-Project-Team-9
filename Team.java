public class Team{
    int teamNum;
    Account[] players; //array of accounts
    private int MAXCOUNT = 5;

    public void addPlayer(Account newPlayer){
        if(players.length < MAXCOUNT){
            players[players.length] = newPlayer;
        }
    }
    public Team(int teamNum){
        this.teamNum = teamNum;
        players = new Account[MAXCOUNT];
    }
}


