/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package theleagueblackjack;


import java.util.Objects;

/**
 *
 *
 */

// Card Class contain two attributes suit and rank 
public final class Card {
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

    // Required for the equal function 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.rank);
        return hash;
    }

    // Checks If two cards are equal or not rank wise
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return this.rank.cardWorth() == other.rank.cardWorth();
    }

  // Return the class rank and suit
    @Override
    public String toString() {
        return this.rank.toString() + this.suit.toString();
    }

   
}
