/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theleagueblackjack;

/**
 *
 * @author EdselR
 */
public class Dealer {
	
	// Constructor
	Dealer(Deck deck){
		this.deck = deck;
	}
	
	Deck deck = new Deck(); // current cards on the player/dealer's hand during the game
	protected boolean win = false; // Determines whether the player/dealer wins
	protected boolean drawAgain = false; // Determines if the dealer should draw another card
	protected boolean isBusted = false;
	protected double netLoss = 0; // Amount of money or points lost 
	protected double netGained = 0; // Amount of money or points gained from winning
	protected double currentPoints = 0; // Current points/money on that the dealer has
	protected int cardValue = 0; // Must be equal to or lesser than 21 to win a round
	
	public boolean gameOver = false;
	
	// Declare all setters
	
	public void setCardValue(int value){
		cardValue = value;
	}
	public void setCurrentPoints(double pts){
		currentPoints = pts;
	}
	public void setDeck (Deck newDeck){
		deck = newDeck;
	}
	//Set a draw decision such that the lower the cardValue, the higher chance the user will draw another card
	public void setDrawDecision(){
		
		double percentOfMax = cardValue/21.0; // How close the value is to 21
		
		// While the 
		if(cardValue < 21){
			// The higher the user gets to 21, the lesser chance there is of him/her drawing another card
			drawAgain = (Math.random()) > percentOfMax? true: false;
		}
		else{
			isBusted = true; // The player is now busted in the current round
			drawAgain = false;
		}
	}
	
	// Declare all getters
	
	public int getCardValue(){
		return cardValue;
	}
	public double getCurrentPoints(){
		return currentPoints;
	}
	public Deck getDeck(){
		return this.deck;
	}
	public boolean getDrawDecision(){
		return drawAgain;
	}
	public double getNetGained(){
		return netGained;	// Returns an amount paid to the winner and subtracts that amount from the player's current money/points
	}
	public double getNetLoss(){
		return netLoss;
	}
	
	// Returns an amount paid to the winner and subtracts that amount from the player's current money/points
	public double pay(double amountDue){
		
		// If there is an insufficient point/money available, return 0;
		if (amountDue >= currentPoints){ 
			gameOver = true; // The player is now busted from the table
			double temp = currentPoints;
			currentPoints = 0;
			return temp;
		}
		else{
			currentPoints -= amountDue; // Subtract payment amount from current amount
			netLoss += amountDue; // Add the payment amount to the netLoss record
			return amountDue;
		}
	}
	public void takeMoney(double amount){
		netGained += amount;
		currentPoints += amount;
	}
	public void printCurrentPoints(){
		System.out.println("Current money: " + this.currentPoints + " $");
	}
}
