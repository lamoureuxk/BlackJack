package edu.wit.comp2000.lists.blackjack;

/** 
 * @author Karl Lamorueux
 */
public class Card implements Comparable<Card>
{
	private int value;
	private Suit suit;
	private boolean ace;
	
	public static void main(String[] args) 
	{
		System.out.println(new Card(3, Suit.Clubs, false));
	}
	
	public Card(int val, Suit s, boolean a) 
	{
		value = val;
		suit = s;
		ace= a;
	}
	
	public String toString() {return value+" of "+suit;}
	
	public boolean isAce() {return ace;} 
	
	public int getValue() {return value;}
	
	public Suit getSuit() {return suit;}
	
	@Override
	public int compareTo(Card other) { return ((Integer)this.value).compareTo(other.value); }
	
}
