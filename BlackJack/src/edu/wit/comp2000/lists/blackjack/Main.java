package edu.wit.comp2000.lists.blackjack;

import java.util.*;

public class Main {

	/**
	 * 
	 * @param args
	 */
    public static void main(String... args) 
    {
        Deck deck = new Deck();
        List<Player> playerList = new ArrayList<>();
        Player dealer = new Player("DEALER");
        Scanner input = new Scanner(System.in);
 
        //asks for valid number of players
        boolean invalid=false;
        int numberOfPlayers=0;
        do {
        	invalid=false;    
        	System.out.print("How many Players? (1 to 7) ");
        	numberOfPlayers=input.nextInt();
        	
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

        input.reset();
        // Main game logic
        while (!playerList.isEmpty()) 
        {
        	//deal initial hands
        	dealInitialTwoCards(dealer, deck);
            for(Player p : playerList) { dealInitialTwoCards(p, deck);}
            
            //ask each player if they want to hit or stay until they bust or are satisfied
            for(Player player : playerList) 
            {
            	
            	hitOrStay(player, deck);
            }
            
            dealerHit(dealer, deck);
            System.out.println("Dealer's hand is "+dealer.getHand());
            
            ArrayList<Player> losers = new ArrayList<>();
            
            for(Player p : playerList) 
            {
            	if(win(p,dealer)) 
            	{
            		
            		System.out.println(p+" wins against dealer");
            	}
            	else 
            	{
            		System.out.println(p+" loses against dealer, and leaves");
            		
            		//Player who loses is removed
            		losers.add(p);
            		
            	}
            }
            System.out.println();
            
            //deal with winners and losers
            for(Player p : losers) 
            {
            	playerList.remove(p);
            }
            losers.clear();
            
            //clear hands
            clearHand(dealer,deck);
            if(!playerList.isEmpty())
            	for(Player p : playerList) { clearHand(p,deck); }
            
        }
        System.out.println("DONE!");
        input.close();
    }
    
    
    /**
     * 
     * @param player
     * @param deck
     */
    private static void hitOrStay(Player player, Deck deck) 
    {
    	Scanner in = new Scanner(System.in);
    	String choice="h";
    	
    	while(choice.equals("h")) 
    	{
    		if(player.getHandValue()==21) 
    		{
    			System.out.println("\n"+player+"'s hand is: "+player.getHand());
    			System.out.println("\n"+player+" is at 21!\n");
    			break;
    		}
	    	System.out.println(player+"'s hand is: "+player.getHand()+"\n\nWould "+player+" like to (h)it or (s)tay?");
	    	
	    	choice = in.nextLine();
	    	
	    	if(choice.equals("h")) //they want to hit
	    	{
	    		player.addToHand(deck.drawCard());
	    		if(player.getHandValue()>21) 
	    		{
	    			System.out.println("\n"+player.getHand());
	    			System.out.println("\n"+player+" Busts\n");
	    			break;
	    		}
	    	}
	    	else// they want to stay
	    	{
	    		System.out.println("\n"+player.getHand());
	    		System.out.println("\n"+player+"'s total hand value is "+player.getHandValue()+"\n");
	    	}
	    	
	    	
    	}
    	//in.close();
    }
    
    /**
     * 
     * @param player
     * @param dealer
     * @return
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
     * 
     * @param dealer
     * @param deck
     * @return
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
     * 
     * @param p
     * @param d
     */
    private static void dealInitialTwoCards(Player p, Deck d) 
    {
    	p.addToHand(d.drawCard()); p.addToHand(d.drawCard());
    }
    
    /**
     * 
     * @return
     */
	private static Player createPlayer() 
    {
		Scanner in = new Scanner(System.in);
    	// Give 'player a name
        System.out.print("please enter your name: ");
        Player p = new Player(in.nextLine());
        //in.close();
        return p;
    }
	
	/**
	 * 
	 * @param p
	 * @param d
	 */
	private static void clearHand(Player p, Deck d) 
	{
		while(!p.getHand().isEmpty()) 
        {
        	d.addCard(p.getHand().remove(0));
        }
	}

}
