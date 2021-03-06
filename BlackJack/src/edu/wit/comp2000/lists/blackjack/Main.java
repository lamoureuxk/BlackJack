package edu.wit.comp2000.lists.blackjack;

import java.util.*;

/**
 * 
 * @author Karl Lamoureux
 * @co-author Daniel
 *
 */
public class Main {

	/**
	 * 
	 * @param args
	 */
    public static void main(String... args) 
    {
    	//Initialization
        Deck deck = new Deck();
        List<Player> playerList = new ArrayList<>();
        ArrayList<Player> winners = new ArrayList<>();
        ArrayList<Player> losers = new ArrayList<>();
        ArrayList<Player> quitters = new ArrayList<>();
        Player dealer = new Player("DEALER", 0);
        Scanner input = new Scanner(System.in);
 
        //asks for valid number of players
        boolean invalid=false;
        int numberOfPlayers=0;
        do {
        	invalid=false;
        	System.out.print("How many Players? (1 to 7) ");
        	
        	//block handles non-integer input
        	try 
        	{
        		numberOfPlayers=input.nextInt();
        	}
        	catch(Exception e) 
        	{
        		invalid=true;
        		input.nextLine();//clears scanner
        		continue;
        	}
        	
        	if(numberOfPlayers<1 || numberOfPlayers>7) 
        	{ invalid=true; }
        	
        }while(invalid);
        
        //adds players to list
        for(int i=1; i<=numberOfPlayers; i++) 
        {
        	System.out.print("Player "+i+" ");
        	playerList.add(createPlayer());
        }
        System.out.println();

        //This is put in "just in case"
        input.reset();
        
        // Main game logic
        while (!playerList.isEmpty()) 
        {
        	deck.shuffle();
        	
        	//Asks if players want to play or quit
        	for(Player p : playerList) { if(!(p.betOrQuit())) quitters.add(p); }
        	
        	//removes quitters
        	for(Player p: quitters) { playerList.remove(p); }
        	quitters.clear();
        	
        	//if all players quit
        	if(playerList.isEmpty()) 
        	{
        		System.out.println("All Players have quit");
        		break;
        	}
        	
        	//deal initial hands, set double down to default of false
        	dealInitialTwoCards(dealer, deck);
            for(Player p : playerList) { dealInitialTwoCards(p, deck); p.doubleDown(false);}
            
            //ask each player if they want to hit or stay until they bust or are satisfied
            for(Player player : playerList) 
            {
            	hitOrStay(player, deck);
            }
            
            //dealer takes turn, hits until hand value is 16 or greater, then stays
            dealerHit(dealer, deck);
            System.out.println("Dealer's hand is "+dealer.getHand()+" value: "+dealer.getHandValue());
            if(dealer.getHandValue()>21) { System.out.println("Dealer busts\n"); }
            else { System.out.println(); }
            
            //Displays winners and losers, adds them to winners or losers list
            for(Player p : playerList) 
            {
            	if(win(p,dealer)) 
            	{
            		System.out.println(p+" wins against dealer");
            		
            		winners.add(p);
            	}
            	else 
            	{
            		System.out.println(p+" loses against dealer");
            		
            		losers.add(p);
            		
            	}
            }
            System.out.println();
            
            //deal with winners 
            for(Player p : winners) 
            {
            	clearHand(p, deck);
            	
            	p.winsBet();
            	if((p.chips()-p.initialChips()) >1000)
            	{
            		System.out.println(p+" has won as many chips that they are allowed to win (1000), and has to leave with "+p.chips()+" chips!");
            		playerList.remove(p);
            	}
            }
            winners.clear();
            
            //deal with losers
            for(Player p : losers) 
            {
            	clearHand(p, deck);
            	p.losesBet();
            	
            	if(p.chips()<=0) 
            	{
            		System.out.println(p+" has lost all chips, and leaves");
            		playerList.remove(p);
            	}
            }
            losers.clear();
            
            //clear hands
            clearHand(dealer,deck);
            if(!playerList.isEmpty())
            	for(Player p : playerList) { clearHand(p,deck); }
            
        }//Game is over after this
        
        System.out.println("DONE!");
        input.close();
    }
    
    
    /**
     * This is the Player's turn. Would they like to Hit, Stay, or Double Down if applicable
     * @param player
     * @param deck
     */
    private static void hitOrStay(Player player, Deck deck) 
    {
    	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	String choice="h";
    	boolean firstTime=true;
    	
    	while(choice.equals("h")) 
    	{
    		if(player.getHandValue()==21) 
    		{
    			System.out.println("\n"+player+"'s hand is: "+player.getHand());
    			System.out.println("\n"+player+" is at 21!\n");
    			break;
    		}
    		
    		//Cannot double down, either too few chips or it is not first hit
    		if(player.chips()< (2*player.ante())||!firstTime) 
    		{
	    		System.out.println(player+"'s hand is: "+player.getHand()+"\n\nWould "+player+" like to (h)it or (s)tay?");
	    		
	    		//this block handles choices
		    	choice = "t";
		    	while(!(choice.equals("h")||choice.equals("s"))) 
		    	{
		    		choice = in.nextLine();
		    		if(!(choice.equals("h")||choice.equals("s"))) 
		    		{
		    			System.out.println("\nInvalid choice, Would "+player+" like to (h)it or (s)tay?");
		    		}
		    	}
    		}
    		
    		//Can double down, first hit with enough chips
    		else if(firstTime)
    		{
    			System.out.println(player+"'s hand is: "+player.getHand()+"\n\nWould "+player+" like to (h)it, (s)tay, (d)ouble down?");
    			
    			//this block handles choices
    	    	choice = "t";
    	    	while(!(choice.equals("h")||choice.equals("s")||choice.equals("d"))) 
    	    	{
    	    		choice = in.nextLine();
    	    		if(!(choice.equals("h")||choice.equals("s")||choice.equals("d"))) 
    	    		{
    	    			System.out.println("\nInvalid choice, Would "+player+" like to (h)it, (s)tay, (d)ouble down");
    	    		}
    	    	}
    		}
    		
    		firstTime=false; 
    		
	    	//they want to hit
	    	if(choice.equals("h")) 
	    	{
	    		player.addToHand(deck.drawCard());
	    		if(player.getHandValue()>21) 
	    		{
	    			System.out.println("\n"+player+"'s hand is "+player.getHand());
	    			System.out.println("\n"+player+" Busts\n");
	    			break;
	    		}
	    	}
	    	
	    	//if they want to double down
	    	else if(choice.equals("d")) 
	    	{
	    		player.addToHand(deck.drawCard());
	    		player.doubleDown(true);
	    		
	    		if(player.getHandValue()>21) 
	    		{
	    			System.out.println("\n"+player.getHand());
	    			System.out.println("\n"+player+" Busts\n");
	    			break;
	    		}
	    		
	    		else if(player.getHandValue()==21) 
	    		{
	    			System.out.println("\n"+player+"'s hand is: "+player.getHand());
	    			System.out.println("\n"+player+" is at 21!\n");
	    			break;
	    		}
	    		
	    		else 
	    		{
		    		System.out.println("\n"+player.getHand());
		    		System.out.println("\n"+player+"'s total hand value is "+player.getHandValue()+"\n");
		    		break;
	    		}
	    	}//end of double down block
	    	
	    	// they want to stay
	    	else
	    	{
	    		System.out.println("\n"+player.getHand());
	    		System.out.println("\n"+player+"'s total hand value is "+player.getHandValue()+"\n");
	    	}
	    	
	    	
    	}
    }
    
    /**
     * 
     * @param player
     * @param dealer
     * @return True if player beats dealer, false otherwise
     */
    private static boolean win(Player player, Player dealer) 
    {
    	if(player.getHandValue()>21) 
    	{
    		return false;
    	}
    	else if(dealer.getHandValue()>21) 
    	{
    		return true;
    	}
    	else if(player.getHandValue()>dealer.getHandValue()) 
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    /**
     * This is the dealer's turn. They hit until their hand's value is 16 or greater
     * @param dealer
     * @param deck
     * @return Dealer's final hand value
     */
    private static int dealerHit(Player dealer, Deck deck) 
    {
    	while(true) 
    	{
	    	if(dealer.getHandValue()<=15) 
	    	{
	    		dealer.addToHand(deck.drawCard());
	    	}
	    	else 
	    	{
	    		return dealer.getHandValue();
	    	}
    	}
    }
    
    /**
     * Deals two cards from deck to player
     * @param p - Player
     * @param d - Deck
     */
    private static void dealInitialTwoCards(Player p, Deck d) 
    {
    	p.addToHand(d.drawCard()); p.addToHand(d.drawCard());
    }
    
    /**
     * Asks for Player name, makes new player
     * @return - The new Player
     */
	private static Player createPlayer() 
    {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	// Give 'player a name
        System.out.print("please enter your name: ");
        String name = in.nextLine();
        int chips=-1;
        while(chips<=0 || chips>500) 
        {
	        try 
	        {
	        	System.out.println("How many chips would you like to buy? (max 500)");
	        	chips= in.nextInt();
	        }
	        catch(Exception e) 
	        {
	        	in.nextLine();
	        }
        }
        Player p = new Player(name,chips);
        return p;
    }
	
	/**
	 * Returns cards from players hand to the deck
	 * @param p - Player
	 * @param d - Dealer
	 */
	private static void clearHand(Player p, Deck d) 
	{
		while(!(p.getHand().isEmpty())) 
        {
        	d.addCard(p.getHand().remove(0));
        }
	}

}
