package base;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */

// Card Class contain two attributes suit and rank 
public final class Card  implements Comparable<Card> {
    private final Suit suit; // Contain suit of the card
    private final Rank rank; // contain card rank 

// Class constructor 
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
// Copy Constructor 
    public Card(Card obj) {
        this.suit = obj.suit;
        this.rank = obj.rank;
    }

// Getters 
    public Suit getSuit() {
        return suit;
    }
    
    public Rank getRank() {
        return rank;
    }

    

  // Return the class rank and suit
    @Override
    public String toString() {
        return this.rank.toString() + this.suit.toString();
    }

    @Override
    public int compareTo(Card obj) {
       if (obj == null) {
            return -1;
        }
        if (getClass() != obj.getClass()) {
            return -1;
        }
        final Card other = (Card) obj;
        if (this.rank.cardWorth() == other.rank.cardWorth())
            return 1;
                    else
            return -1;
           
    }

   
}
