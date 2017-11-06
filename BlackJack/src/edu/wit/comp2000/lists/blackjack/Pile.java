package edu.wit.comp2000.lists.blackjack;

import java.util.*;

/**
 * @author Karl Lamoureux
 */
public class Pile {
	
	protected ArrayList<Card> cards;
	
	public boolean addCard(Card e) { return cards.add(e); }
	
	public Card drawCard() 
	{ 
		if(!cards.isEmpty()) return cards.remove(0); 
		
		else return null; //to be changed later if needed
	}
	
	public void shuffle() { Collections.shuffle(cards); }
	
	public void sort() { Collections.sort(cards); }
	
	public boolean isEmpty() { return cards.isEmpty(); }
	
	public Pile() {cards = new ArrayList<Card>();}
	
	@Override
	public String toString() 
	{
		if(this.isEmpty()) return "Pile is Empty";
		
		String output="";
		
		for(Card c  : cards) { output+= (c+", "); }
		
		return output.substring(0, output.length()-2);
	}
	
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
		
		
		
	}

}
