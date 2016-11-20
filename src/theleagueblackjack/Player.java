

package theleagueblackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Player{
 
    private String username = "";
    private String password = "";
    private int wins = 0; // The amount of times the player has won
    private int losses = 0; // The amount of times the player has lost
    private double betAmount = 0; // The amount of money/points the player wants to put down
    private double currentPoints = 0; // Current points the player has
    private List<Card> cards = new ArrayList<>();
    private boolean isStanding = false;
    private Scanner input = new Scanner(System.in);
    
    //No-arg constructor
    Player(){
        setUsername();
        setPassword();
        setBetAmount();
    }
    
    //Arg-constructor 
    Player(double amt, String usr, String pw, int win, int loss, double pts){
        setBetAmount(amt);
        setUsername(usr);
        setPassword(pw);
        setWins(win);
        setLosses(loss);
        setCurrentPoints(pts);
    }
    
    //Returns the combined value of cards in your hand
    public int cardsValue() {
        int sum = 0;
        int acesCount = 0;

        for (Card c : cards) {
            if (c.getRank() == Rank.ACE) {
                acesCount++;
            }
            sum += c.getRank().cardWorth();
        }
        // Count aces as one if busted.
        while (sum > 21 && acesCount > 0) {
            acesCount--;
            sum -= 10;
        }
        return sum;
    }
    
    //      Setters     //
    
    public void setUsername(String name){
        username = name;
    }
    public void setUsername(){
        System.out.print("Username:");
        this.username = input.nextLine();
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPassword(){
        System.out.print("Password:");
        this.password = input.nextLine();
    }
    
    public void setCurrentPoints(double pts){
		currentPoints = pts;
	}
    
    public void setBetAmount(double amt){
        this.betAmount = amt;
    }
    public void setBetAmount(){
        System.out.print("Amount to bet:");
        while(!input.hasNextDouble()){
            input.nextLine();
            System.out.print("\nRetry: ");
        }
        this.betAmount = input.nextDouble();
    }
    public void setStanding(boolean isStanding){
        this.isStanding = isStanding;
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
 
    //      Getters        //     
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
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
    
    public double getBetAmount(){
        return betAmount;
    }
    
    public List<Card> getCards() {
        return this.cards;
    }
    
    
    //Functions to check for different status
    
    public boolean isStanding(){
        
        //If the player is already busted, automatically returns false
        if(isBusted()){
            return false;
        }
        return isStanding;
    }
    public boolean isBusted() {
        return cardsValue() > 21;
    }
    public boolean isBlackJack() {
        return  cardsValue() == 21 && 
                cards.size() == 2;
    }
    
    // NOTE: Remember to pop the card from the deck object that is being used
    // right after this function is called in the main() class
    public void drawCardFrom(Deck deck) {
        Card card = deck.drawCard();
        cards.add(card);
    }
   
    public void addCard(Card card) {
        cards.add(card);
    }
   
    // Gives a list of available next moves the player can use
    public List<HandAction> getAvailableMoves(double playerFunds) {
        
        List<HandAction> movesList = new ArrayList<>();
        if (!isBusted() && !isStanding()) {
            movesList.add(HandAction.HIT);
            movesList.add(HandAction.STAND);
            
        if (playerFunds >= this.betAmount)
            movesList.add(HandAction.DOUBLE);

        }
        return movesList;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append("\n");
        }
        sb.append("Value: ").append(cardsValue());
        return sb.toString();
    }

        // Player Actions - draws from HandAction class

}
