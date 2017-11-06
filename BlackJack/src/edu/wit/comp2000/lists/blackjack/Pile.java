package edu.wit.comp2000.lists.blackjack;

import java.util.*;

/**
 * @author Karl Lamoureux
 */
public class Pile 
{
	protected ArrayList<Card> cards;
	
	public Pile() {cards = new ArrayList<Card>();}
	
	public boolean addCard(Card e) { return cards.add(e); }
	
	public Card drawCard() 
	{ 
		if(!cards.isEmpty()) return cards.remove(0); 
		
		else return null; //to be changed later if needed
	}
	
	/**
	 * Shuffles list
	 */
	public void shuffle() { Collections.shuffle(cards); }
	
	/**
	 * Sorts list by value
	 */
	public void sort() { Collections.sort(cards); }	
	
	/**
	 * @return true is list is empty, false if not empty
	 */
	public boolean isEmpty() { return cards.isEmpty(); }
	
	/**
	 * @return int representing number of Cards
	 */
	public int size() {return cards.size();}
	
	/**
	 * If pile is empty, prints "Pile is Empty", otherwise calls toString for every Card in the pile, adding commas and spaces in between
	 */
	@Override
	public String toString() 
	{
		if(this.isEmpty()) return "Pile is Empty";
		
		String output="";
		
		for(Card c  : cards) { output+= (c+", "); }
		
		return output.substring(0, output.length()-2);
	}
	
	/**
	 * Tests constructor, addCard, drawCard, shuffle, sort, isEmpty, size, and toString
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Pile p = new Pile();
		
		System.out.println("Pile created, has cards: "+p);
		
		System.out.println("Adding Queen of Hearts, Ace of Spades, and 3 of Diamonds");
		
		p.addCard(new Card(12, Suit.Hearts, false));
		p.addCard(new Card(14, Suit.Spades, true));
		p.addCard(new Card(3, Suit.Diamonds, false));
		
		System.out.println("Pile has cards: "+p);
		
		p.sort();
		System.out.println("Sorting Pile by blackjack value: "+p); 
		p.shuffle();
		System.out.println("Shuffling Pile: "+p);
		
		System.out.println("Card returned by p.drawCard(): "+p.drawCard());
		System.out.println("Remaining cards in pile: "+p);
		System.out.println("Results of p.size(): "+p.size());
		System.out.println("Results of p.isEmpty(): "+p.isEmpty());
		p.drawCard(); p.drawCard();
		System.out.println("Results of p.isEmpty() after drawing all cards: "+p.isEmpty());
		System.out.println("Results of p.size(): "+p.size());
		
		System.out.println("Results of drawing card when pile is empty: "+p.drawCard());
		
	}

}
