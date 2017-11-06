package edu.wit.comp2000.lists.blackjack;

/**
 * 
 * @author Karl Lamoureux
 *
 */
public class Deck extends Pile
{
	/**
	 * Populates list with standard 52 playing cards, then shuffles the list
	 */
	public Deck() 
	{
		for(int i=2; i<=14; i++) { cards.add(new Card(i, Suit.Clubs, i==14)); }
		
		for(int i=2; i<=14; i++) { cards.add(new Card(i, Suit.Hearts, i==14)); }
		
		for(int i=2; i<=14; i++) { cards.add(new Card(i, Suit.Diamonds, i==14)); }
		
		for(int i=2; i<=14; i++) { cards.add(new Card(i, Suit.Spades, i==14)); }
		
		shuffle();
	}
	
	public static void main(String [] args) 
	{
		Deck x = new Deck();
		System.out.println(x+"\n");
		x.shuffle();
		System.out.println(x+"\n");
	}
	
}//end of class