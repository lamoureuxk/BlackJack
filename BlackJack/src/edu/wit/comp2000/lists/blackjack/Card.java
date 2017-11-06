package edu.wit.comp2000.lists.blackjack;

/** 
 * @author Karl Lamorueux
 */
public class Card implements Comparable<Card>
{	
	private int value;
	private faceCard face;	//for now this is just for toString because jacks, queens, and kings have value 10
	private Suit suit; 
	private boolean ace;
	
	/**
	 * 
	 * @param val - value from 2-14, will translate to cards value from 2-10, val>10 will become 10, val 11 12 or 13 will set face to Jack, Queen, or King respectively
	 * @param s - enum for the suit
	 * @param a - boolean for if the card is an ace
	 */
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
	
	/**
	 * @return boolean representing if card is an ace
	 */
	public boolean isAce() {return ace;} 
	
	/**
	 * @return blackjack numerical value of card (ace is 11 by default)
	 */
	public int getValue() {return value;}
	
	/**
	 * @return enum for suit
	 */
	public Suit getSuit() {return suit;}
	
	/**
	 * Cards are compared on their "value" data field
	 */
	@Override
	public int compareTo(Card other) { return ((Integer)this.value).compareTo(other.value); }
	
	/**
	 * Checks if cards have equal value and face enum aka 10 != Jack != Queen != King, even though they all have value 10
	 */
	@Override 
	public boolean equals(Object other) { return value==((Card)other).value && face == ((Card)other).face; }
	
	/**
	 * Will output the common phrase for the card, aka "Ace of Spades" "10 of Diamonds" "Queen of Hearts"
	 */
	@Override
	public String toString() 
	{
		if(ace) return "Ace of "+suit;
		
		else if(face!=faceCard.Number) { return face+" of "+suit; }
		
		return value+" of "+suit;
	}
	
	/**
	 * Used for toString
	 * @author Karl Lamoureux
	 *
	 */
	private enum faceCard{King, Queen, Jack, Number}
	
	/**
	 * Tests creating new cards, using toString, using compareTo, and using equals
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println(new Card(3, Suit.Clubs, false));	  //3 of Clubs
		System.out.println(new Card(14, Suit.Diamonds, true));//Ace of Diamonds
		System.out.println(new Card(3, Suit.Hearts, true));	  //Ace of Hearts  (Even though value should be 3)
		System.out.println(new Card(12, Suit.Spades, false)+"\n"); //Queen of Spades
		
		Card queen = new Card(12, Suit.Clubs, false);
		Card queen2 = new Card(12, Suit.Hearts, false);
		Card three = new Card(3, Suit.Hearts, false);
		Card king = new Card(13, Suit.Diamonds, false);
		Card ace = new Card (14, Suit.Spades, true);
		System.out.println(queen+" value: "+queen.getValue()+"\n"); //value is 10 for blackjack
		
		System.out.println("Result of queen.compareTo(three): "+queen.compareTo(three));//1
		System.out.println("Result of queen.compareTo(king): "+queen.compareTo(king));	//0
		System.out.println("Result of three.compareTo(three): "+three.compareTo(three));//0
		System.out.println("Result of three.compareTo(king): "+three.compareTo(king));	//-1
		System.out.println("Result of queen.compareTo(ace): "+queen.compareTo(ace));	//-1
		System.out.println("Result of queen.equals(king): "+queen.equals(king));	//false
		System.out.println("Result of queen.equals(queen2): "+queen.equals(queen2));	//true
	}
	
}
