package edu.wit.comp2000.lists.blackjack;

/** 
 * @author Karl Lamorueux
 */
public class Card implements Comparable<Card>
{	
	private int value;
	private faceCard face;
	private Suit suit; //enum
	private boolean ace;
	
	public Card(int val, Suit s, boolean a) 
	{
		value = val;
		suit = s;
		ace= a;
		
		switch (val) 
		{
		case 11: face= faceCard.Jack; break;
		case 12: face= faceCard.Queen; break;
		case 13: face= faceCard.King; break;
		default: face= faceCard.Number; break;
		}
		
		if(value>10) {value=10;}
		if(ace) {value=11;}
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
		
		else if(face!=faceCard.Number) 
		{
			return face+" of "+suit;
		}
		
		return value+" of "+suit;
	}
	
	private enum faceCard{King, Queen, Jack, Number}
	
	public static void main(String[] args) 
	{
		System.out.println(new Card(3, Suit.Clubs, false));
	}
	
}
