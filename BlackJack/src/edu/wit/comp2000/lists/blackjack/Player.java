package edu.wit.comp2000.lists.blackjack;

import java.util.List;

/**
 * 
 * @author Daniel
 *
 */
public class Player {
    private Hand hand = new Hand();
    private String name;
    private int chips;
    private int ante;

    Player(String name) {
        this.name = name;
        chips=200;
        ante=0;
    }
    
    public void setAnte(int a) {
    	ante=a;
    }
    
    public void loseBet() {
    	chips-=ante;
    	ante=0;
    }
    
    public void winBet() {
    	chips+=ante;
    	ante=0;
    }
    
    public int chips() {
    	return chips;
    }

    public void addToHand(Card card) {
        hand.addCard(card);
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
    
    public void printHand() 
    {
    	System.out.println(hand);
    }
 
    public int getHandValue() {//Hand has a getValue method that accounts for aces, we may want to use that instead
        return hand.getValue();
    }
}
