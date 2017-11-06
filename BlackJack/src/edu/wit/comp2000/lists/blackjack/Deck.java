package edu.wit.comp2000.lists.blackjack;

/**
 * 
 * @author Karl Lamoureux
 *
 */
public class Deck extends Pile
{
	public Deck() 
	{
		for(int i=2; i<=14; i++) 
		{
			cards.add(new Card(i, Suit.Clubs, i==14));
		}
		for(int i=2; i<=14; i++) 
		{
			cards.add(new Card(i, Suit.Hearts, i==14));
		}
		for(int i=2; i<=14; i++) 
		{
			cards.add(new Card(i, Suit.Diamonds, i==14));
		}
		for(int i=2; i<=14; i++) 
		{
			cards.add(new Card(i, Suit.Spades, i==14));
		}
	}
}
