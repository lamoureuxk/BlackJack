package edu.wit.comp2000.lists.blackjack;

import java.util.*;

/**
 * 
 * @author Daniel
 * @co-author Karl
 */
public class Player {
    private Hand hand = new Hand();
    private String name;
    private int chips;
    private int initialChips;
    private int ante;
    private boolean doubleDown;

    Player(String name, int c) {
        this.name = name;
        chips=c;
        initialChips=c;
        ante=0;
        doubleDown=false;
    }
    
    public int initialChips() {return initialChips;}
    
    public int ante() {return ante;}
    
    /**
     * 
     * @return false if player has quit
     */
    public boolean betOrQuit() {
    	@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
    	String choice="4";
    	while(!(choice.equals("b")||choice.equals("q"))) {
    		System.out.println("Would "+name+" like to (b)et or (q)uit?");
    		choice= input.nextLine();
    	}
    	if(choice.equals("q")) {
    		System.out.println(name+" has quit with "+chips+" chips!\n");
    		return false;
    	}
    	else {
    		ante=-1;
    		while(ante<=0||ante>chips || ante>200) 
            {
    	        try 
    	        {
    	        	System.out.println(name+" has "+chips+" chips\nHow much would "+name+" like to bet? (Max 200 or chips you have)");
    	        	ante= input.nextInt();
    	        }
    	        catch(Exception e) 
    	        {
    	        	input.nextLine();
    	        }
            }
    		return true;
    	}
    }
    
    public void setAnte(int a) {
    	ante=a;
    }
    
    public void losesBet() {
    	if(doubleDown) {
	    	chips-= 2*ante;
	    	if(chips<0) { chips=0; }
	    	System.out.println(name+" has lost "+2*ante+" chips and now has "+chips+" chips");
    	}
    	else {
    		chips-= ante;
    		if(chips<0) { chips=0; }
	    	System.out.println(name+" has lost "+ante+" chips and now has "+chips+" chips");
    	}
    	ante=0;
    }
    
    public void winsBet() {
    	if(doubleDown) {
	    	chips+= 2*ante;
	    	System.out.println(name+" has won "+2*ante+" chips and now has "+chips+" chips");
    	}
    	else {
    		chips+= ante;
    		System.out.println(name+" has won "+ante+" chips and now has "+chips+" chips");
    	}
    	ante=0;
    }
    
    public void doubleDown(boolean choice) 
    {
    	doubleDown= choice;
    }
    
    public int chips() {
    	return chips;
    }

    public void addToHand(Card card) {
        hand.addCard(card);
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
    
    public void printHand() 
    {
    	System.out.println(hand);
    }
 
    public int getHandValue() {//Hand has a getValue method that accounts for aces, we may want to use that instead
        return hand.getValue();
    }
}
