package edu.wit.comp2000.lists.blackjack;

import java.util.ArrayList;

/**
 * 
 * @author Marcus S.
 *
 */
public class Hand extends Pile{

	/**
	 * adds card to hand
	 * @param card - card to be added
	 */
	public void add(Card card) {
		this.addCard(card);
	}

	/**
	 * 
	 * @return - list of cards in hand
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	/**
	 * @return - int representing value of hand, if there are aces, 10 will be subtracted for each ace until the value is under 21
	 */
	public int getValue()
	{
		int value = 0;
		
		for(Card c : cards)
		{
			value += c.getValue();
		}
		
		for(int aces = numberOfAces(); aces>0; aces--)
		{
			if(value > 21)
			{
				value -= 10;
			}
		}
		return value;
	}
	
	/**
	 * @return - number of aces in the hand
	 */
	private int numberOfAces()
	{
		int aces=0;
		for(Card c : cards)
		{
			if(c.isAce())
			{
				aces++;
			}
		}
		return aces;
	}
	
	/**
	 * for testing
	 * @param args
	 */
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		Hand hand = new Hand();
		hand.addCard(deck.drawCard());
		hand.addCard(deck.drawCard());
		System.out.println(hand);
	}

}
