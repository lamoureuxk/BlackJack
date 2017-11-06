package edu.wit.comp2000.lists.blackjack;

/** 
 * @author Karl Lamorueux
 */
public class Card implements Comparable<Card>
{
	private int value;
	private Suit suit; //enum
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
		if(val>10) {val=10;}
		if(ace) {val=11;}
	}
	
	
	public boolean isAce() {return ace;} 
	
	public int getValue() {return value;}
	
	public Suit getSuit() {return suit;}
	
	@Override
	public int compareTo(Card other) { return ((Integer)this.value).compareTo(other.value); }
	
	@Override
	public String toString() 
	{
		if(ace) return "Ace of "+suit;
		
		else if(value>10) 
		{
			if(value==11) return "Jack of "+value;
			if(value==12) return "Queen of "+value;
			if(value==13) return "King of "+value;
		}
		
		return value+" of "+suit;
	}
	
}
