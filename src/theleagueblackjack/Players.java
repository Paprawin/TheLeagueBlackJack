/* Player Class
 * Giancarlo Soriano
 * 
 * 
 */
package theleagueblackjack;

public class Players extends Dealer {
    
    Hand playerHand;
    
    Players() {
        super();
        this.playerHand = new Hand(betAmount);
    }
    
    Players(int numPlayers) {
        
        super();
        numPlayers = this.numPlayers;
        this.playerHand = new Hand(betAmount);
    }
    
    protected String username;
    protected String password;
    protected int numPlayers = 0; // We'll ask how many players are playing (at least 1 player, up to 4 max(?))
    protected int wins = 0; // The amount of times the player has won
    protected int losses = 0; // The amount of times the player has lost
    protected float betAmount; // The amount of money/points the player wants to put down
    protected double currentPoints = 0; // Current points/money that the player has
    
    
    //      Setters     //
    public void setUsername(String name){
        username = name;
    }
    
    public void setPassword(String pword){
        password = pword;
    }
    
    public void setNumPlayers(int num){
        numPlayers = num;
    }
    
    public void addWins(){
        wins += 1;
    }
    
    public void addLosses(){
        losses += 1;
    }
 
    //      Getters        //     
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getNumPlayers(){
        return numPlayers;
    }
    
    public int getWins(){
        return wins;
    }
    
    public int getLosses(){
        return losses;
    }
    
    public double getCurrPoints(){
        return currentPoints;
    }
    
    // Player Actions - draws from HandAction class
}
