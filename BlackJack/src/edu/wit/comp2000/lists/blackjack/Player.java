package edu.wit.comp2000.lists.blackjack;

import java.util.List;

public class Player {
    private Hand hand = new Hand();
    private String name;

    Player(String name) {
        this.name = name;
    }

    public void addToHand(Card card) {
        hand.addCard(card);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
 
    public int getHandValue() {//Hand has a getValue method that accounts for aces, we may want to use that instead
        int value = 0;
        for (Card card: hand.getCards()) {
            value += card.getValue();
        }
        return value;
    }
}
