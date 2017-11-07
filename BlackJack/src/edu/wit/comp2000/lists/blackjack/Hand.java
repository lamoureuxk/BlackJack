package edu.wit.comp2000.lists.blackjack;

public class Hand extends Pile{

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
