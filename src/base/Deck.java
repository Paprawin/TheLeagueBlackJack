package base;


import java.util.ArrayList;

import java.util.Random;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
public final class Deck {
    //Array List Containing all the card of the deck
    private final ArrayList<Card> deckCards;
    private static final int CARDS_IN_DECK = 52; // Total card in a Deck
    private static final int TOTAL_RANKS = 13;   // Total Ranks From Ace - King
    private static final int TOTAL_SUITS = 4;    // Total Suits 
    private final Random random;                 // For randomization of cards 
    
    
    // Constructor for the Deck
    public Deck() {
        this.deckCards = new ArrayList<>(CARDS_IN_DECK);
        random = new Random();
        initializeDeck(); // Initialize the Deck by filling the deckCards
        shuffle();        // Set seed for the random
    }
    
    // Set seed for the random
    public void shuffle() {
        random.setSeed(System.currentTimeMillis());
    }

    // Initialize the Deck by filling the deckCards
    private void initializeDeck() {
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();
        
        for (int i = 0; i < TOTAL_SUITS; ++i) {
            for (int j = 0; j < TOTAL_RANKS; ++j) {
                this.deckCards.add(new Card(suits[i], ranks[j]));
            } 
        }
    }
    // draw a Random card from the deck
    public Card drawCard() {
        int randomIndex = random.nextInt(CARDS_IN_DECK);
        return new Card(deckCards.get(randomIndex));
    }
}
