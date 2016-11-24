package base;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
// Hand class for the hand being played 
public class Hand {
    // Contain the uid for the hand 
    private final String uid;
    // Cards used inside the hand 
    private final ArrayList <Card> cards;
    // Bet amount in the hand
    private float betAmount;
    // Is the hand been Splitted
    private boolean beenSplitted;
    // Is the hand on Stand
    private boolean isStanding;
    
    
    // Hand Class the contructor 
    private Hand(float betAmount, boolean beenSplitted) {
        this.cards = new ArrayList<>();
        this.isStanding = false;
        this.beenSplitted = beenSplitted;
        this.betAmount = betAmount;
        this.uid = UUID.randomUUID().toString(); 
    }
    
    // Getters 
    public String getUID() {
        return this.uid;
    }
    
    public boolean isStanding() {
        return this.isStanding;
    }
    
     public List<Card> getCards() {
        return this.cards;
    }
    
    public float getBetAmount() {
        return this.betAmount;
    }
     
    // Setters 
    public void setStanding(boolean value) {
        this.isStanding = value;
    }
    
    public void setBetAmount(float newAmount) {
        this.betAmount = newAmount;
    }
    
    public boolean equalsUID(Hand otherHand) {
        return (this.uid == null ? otherHand.getUID() == null : this.uid.equals(otherHand.getUID()));
    }
    
    // Returns a newEmpty Hand 
    public static Hand newEmptyHand(float betAmount) {
        Hand retHand = new Hand(betAmount, false);
        return retHand;
    }
    
    // Split the hand and return the spilted hand 
    public static Hand splitFrom(Hand otherHand) {
        Hand retHand = new Hand(otherHand.betAmount, true);
        otherHand.beenSplitted = true;
        retHand.cards.add(otherHand.cards.get(1));
        otherHand.cards.remove(1);
        return retHand;
    }
    
    // Return the hand sum 
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
    
       // Checks if the hand total is not over 21 return false if it is over 21
    public boolean isBusted() {
        return cardsValue() > 21;
    }
    
    // Checks if there is Been a BlackJack or not
    public boolean isBlackJack() {
        // if cards has not been splitted and total sum is 21 and there are only two cards in hand
        return  cardsValue() == 21 && 
                beenSplitted == false &&
                cards.size() == 2;
    }
    
    // Checks if card is Splittable or Not 
    public boolean isSplittable() {
        // No of cards must be two and rank of two cards must be same 
        if (cards.size() != 2)
            return false;
        return cards.get(0).getRank().cardWorth() == cards.get(1).getRank().cardWorth();
    }
    
    // Return the Legals moves allowed in a given situation
    public List<HandAction> getLegalActions(float playerFunds) {
        List<HandAction> retList = new ArrayList<>();
        // if hand total is not over 21 and if the player is not already standing
        // then he can hit or stand
        if (!isBusted() && !isStanding()) {
            retList.add(HandAction.HIT);
            retList.add(HandAction.STAND);
         // if player funds is greater then the bet amount then he can double   
         // then he can double 
            if (playerFunds >= this.betAmount)
                retList.add(HandAction.DOUBLE);
         // if hand is Splittable and player funds is greater then the bet amount
         // then he can split 
            if (isSplittable() && playerFunds >= this.betAmount) {
                retList.add(HandAction.SPLIT);
            }
        }
        return retList;
    }
  
    // Draw form the deck and add in the hand 
    public void drawCardFrom(Deck deck) {
        Card card = deck.drawCard();
        addCard(card);
    }
    
    // Checks if the hand total is not over 21 return false if it is over 21
    public boolean isPlayable() {
        return !isBusted();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : cards) {
            sb
            .append(c.toString())
            .append("\n");
        }
        sb.append("Value: ").append(cardsValue());
        return sb.toString();
    }
    
    // Add card in the hand 
    public void addCard(Card card) {
        cards.add(card);
    }


}
