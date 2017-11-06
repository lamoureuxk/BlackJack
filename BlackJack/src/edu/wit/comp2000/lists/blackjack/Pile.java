package edu.wit.comp2000.lists.blackjack;

import java.util.*;

/**
 * 
 * @author Karl Lamoureux
 *
 */
public class Pile {
	
	protected ArrayList<Card> cards;
	
	public boolean addCard(Card e) { return cards.add(e); }
	
	public Card removeCard() { return cards.remove(0); }
	
	public void shuffle() { Collections.shuffle(cards); }
	
	public boolean isEmpty() { return cards.isEmpty(); }
	
	public Pile() {cards = new ArrayList<Card>();}
	
	@Override
	public String toString() 
	{
		String output="";
		for(Card c  : cards) 
		{
			output+= (c+", ");
		}
		
		output= output.substring(0, output.length()-2);
		
		return output;
	}

}
