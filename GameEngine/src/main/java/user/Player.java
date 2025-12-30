package user;

public class Player {

    private User id;

    private int totalTImeTaken;

    private String playerSymbol;

    public Player(String playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    public String symbol(){
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol.equals("X") ? "0" : "X");
    }

    public void setTImeTaken(int timeInMillis){
        totalTImeTaken += timeInMillis;
    }
    public int getTotalTImeTaken(){
        return totalTImeTaken;
    }
}