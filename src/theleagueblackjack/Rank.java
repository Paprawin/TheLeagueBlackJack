/*
Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package theleagueblackjack;

/**
 *
 * @author 
 */
// Enums used for the Rank of the card
public enum Rank {
                   //  Numeric Value
        ACE,       //       0
        TWO,       //       1
        THREE,     //       2
        FOUR,      //       3
        FIVE,      //       4
        SIX,       //       5
        SEVEN,     //       6  
        EIGHT,     //       7
        NINE,      //       8
        TEN,       //       9
        JACK,      //       10
        QUEEN,     //       11
        KING;      //       12

        // Return the class numeric Value 
        public int cardWorth() {
            
            int numericValue = 0;

            // for Ace 
            if (this.ordinal() == 0) {
                numericValue = 11;
            }
            // for values range from TWO - NINE
            else if (this.ordinal() >= 1 && this.ordinal() <= 9) {
                numericValue = this.ordinal() + 1; // Adding 1 in the original value 
            }
            // for values range from TEN - KING
            else if (this.ordinal() <= 12 && this.ordinal() >= 10) {
                numericValue = 10;
            }
            
            return numericValue;
        }
    }