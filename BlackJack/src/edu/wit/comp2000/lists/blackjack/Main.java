package edu.wit.comp2000.lists.blackjack;

import java.util.*;

public class Main {

    public static void main(String... args) {
        Deck deck = new Deck();
        List<Player> playerList = new ArrayList<>();
        Player dealer = new Player("DEALER");
        Scanner input = new Scanner(System.in);
        Player player = new Player("Player");

        // Add all players to the player list
        playerList.add(dealer);
        playerList.add(player);
        
//        boolean invalid=false;
//        int numberOfPlayers=0;
//        do {
//        	invalid=false;
//        	System.out.print("How many Players? (1 to 7)");
//        	numberOfPlayers=input.nextInt();
//        	
//        	if(numberOfPlayers<1 || numberOfPlayers>7) 
//        	{ invalid=true; }
//        	
//        }while(invalid);
//        
//        for(int i=0; i<numberOfPlayers; i++) 
//        {
//        	playerList.add(createPlayer());
//        }
        

        // Deal 2 cards to each player for the initial hand
        for (int i = 0; i < 1; i++) {
            for (Player p : playerList) {
                player.addToHand(deck.drawCard());
            }
        }

        // Show the players hand
        printCurrentHand(player);
        System.out.print("\n");

        // Main game logic
        while (true) {
            System.out.print("Would you like another (c)ard or would you like to see your (h)and or would you like to (s)tay?");

            String userInput = input.next();

            // Give the player another card and check if the players hand is valid
            if (userInput.equalsIgnoreCase("c")) {
                if (player.getHandValue() <= 21) {
                    player.addToHand(deck.drawCard());
                    // System.out.printf("Added %s the the players hand.%n", card.toString());
                    printCurrentHand(player);
                } else {
                    System.out.printf("You lose your hand value is: %d%n", player.getHandValue());
                    break;
                }
            }
            
            // Print the players hand
            else if (userInput.equalsIgnoreCase("h")) {
                printCurrentHand(player);
            }
            
            // Stop the dealing and evaluate the game's outcome.
            else if (userInput.equalsIgnoreCase("s")) {
                printCurrentHand(player);
                printCurrentHand(dealer);
                if ((dealer.getHandValue() <= 21 && dealer.getHandValue() > player.getHandValue()) || player.getHandValue() > 21 && dealer.getHandValue() <= 21) {
                    System.out.printf("%s's hand wins!%n", dealer.getName());
                } else if ((player.getHandValue() <= 21 && player.getHandValue() > dealer.getHandValue()) || dealer.getHandValue() > 21 && player.getHandValue() <= 21) {
                    System.out.printf("%s's hand wins!%n", player.getName());
                } else {
                    System.out.printf("%s's hand is %d and %s's hand is %d.%n", player.getName(), player.getHandValue(), player.getHand(), player.getHandValue());
                }
                break;
            }
            // For unknown entries so that the while loop doesn't fail
            else {
                System.out.print("");
            }

        }
        System.out.println("DONE!");

    }
    
    
	private static Player createPlayer() 
    {
		Scanner in = new Scanner(System.in);
    	// Give 'player a name
        System.out.print("Please enter your name: ");
        in.close();
        return new Player((in.nextLine()));
        
    }
	


    private static void printCurrentHand(Player player) {
        System.out.printf("%s's current hand is: %s With a total value of: %s%n", player.getName(), player.getHand(), player.getHandValue());
    }
}
