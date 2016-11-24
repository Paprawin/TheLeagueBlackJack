package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
public class Player {
    private static final int NORMAL_WIN_FACTOR = 2;
    private final PlayerType playerType; // Contain the player type human or computer 
    private final String name;           // Player name 
    private final List<Hand> hands;      // Hands of the player
    private  Table table;  // Table on which player is playing 
    private Hand currentHand;            // player current hand
    private int wins = 0; // The amount of times the player has won
    private int losses = 0; // The amount of times the player has lost
    private float funds;                 // player total fund
    private boolean isOutOfRound;  
    //
    
    // Player class constructor
    public Player(String name, PlayerType type, Table table) {
        this.funds = 500f;
        this.name = name;
        this.table = table;
        this.hands = new ArrayList<>();
        this.playerType = type;
        this.currentHand = null;
        this.isOutOfRound = true;
    }
    
    public Player(String name , int win , int lose , float funds , PlayerType type  ) {
       this.funds = funds;
        this.name = name;
        this.playerType = type;
        this.wins = win;
        this.losses = lose;
         this.hands = new ArrayList<>();
         this.table = null;
    }
    
    // Getter
    public float getFunds() {
        return funds;
    }
    
    public List<Hand> getHands() {
        return this.hands;
    }
    
    public boolean isOutOfRound() {
        return this.isOutOfRound;
    }
    
      public Hand getCurrentHand() {
        return this.currentHand;
    }
    
    public String getName() {
        return this.name;
    }
    
    public PlayerType getType() {
        return this.playerType;
    }

    
    // Setters 
    public void setOutOfRound(boolean value) {
        this.isOutOfRound = value;
    }
    
    public void setFunds(float newValue) {
        this.funds = newValue;
    }
    
    public void setTable(Table table) {
        this.table = table;
    }
    
    public void setWins(int wins){
        this.wins = wins;
    }
    
    public void addWins(){
        wins += 1;
    }
    
    public void setLosses(int losses){
        this.losses = losses;
    }
    
    public void addLosses(){
        losses += 1;
    }
 
     public int getWins(){
        return wins;
    }
    
    public int getLosses(){
        return losses;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    // Return true if the two player object are equal
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return Objects.equals(this.name, other.name);
    }
    
    
    
    // clear all the hands and the current hand
    public void dropHands() {
        if (!this.hands.isEmpty()) {
            hands.clear();
        }
        currentHand = null;
    }

    // Place the initial bet when the hand begins 
    public void placeInitialBet(float betValue) {
            this.funds -= betValue; // Auto deduction of the funds 
            Hand newHand = Hand.newEmptyHand(betValue); // creating a new hand 
            this.hands.add(newHand);
            this.currentHand = newHand;
            this.isOutOfRound = false;
        }
    
    // Place the initial bet without the cards when the hand begins 
    public void placeBetWithoutCards(float betValue) {
        this.funds -= betValue;
        this.currentHand = hands.get(0);
        this.isOutOfRound = false;
    }
    
    // Call stand on the hand 
    public void doStand() {
        currentHand.setStanding(true);
    }
    
    // Call hit on the hand
    public void doHit() {
        currentHand.drawCardFrom(table.getDeck()); // drawing card from the deck and adding in the current hand on Hit
    }
    
    // Call double on the hand 
    public void doDouble() {
        currentHand.drawCardFrom(table.getDeck());  // drawing card from the deck and adding in the current hand on Double
        this.funds -= currentHand.getBetAmount();   
        currentHand.setBetAmount(currentHand.getBetAmount() * 2); // Doubling the amount of bet 
    }
    
    // Call split on the hand 
    public void doSplit() {
        this.hands.add(Hand.splitFrom(currentHand)); // Adding splitted hand in the hand arraylist 
        this.funds -= currentHand.getBetAmount();
    }
    
    // Checking if the player has any active hand or not that is playable 
    public boolean isActive() {
        if (this.hands.size() <= 0)
            return false;
        
        for (Hand hand : this.hands) {
            if (hand.isPlayable())
                return true;
        }
        return false;
    }
    
    public boolean hasMoreHands() {
        if (this.hands.size() <= 1)
            return false;
        else
            for (Hand h : this.hands) {
                if (h.getUID() == null ? currentHand.getUID() != null : !h.getUID().equals(currentHand.getUID()))
                    if (h.isPlayable() && !h.isStanding())
                        return true;
            }
        return false;
    }
    
    public void switchHands() {
        for (Hand h : this.hands) {
            if (h.getUID() == null ? currentHand.getUID() != null : !h.getUID().equals(currentHand.getUID()))
                if (h.isPlayable() && !h.isStanding())
                    currentHand = h;
        }
    }
    
  
    
}
