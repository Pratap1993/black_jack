package com.chagu.blackjack;

public class Game {

	/**
	 * For unit testing I have provided '@param userInput' as argument. This can be changed to any inputstream or scanner.
     * @param userInput: is the response user will provide either hit(1) or stand(2)
     */
	public void playBlackJack(double playerBet, int userInput) {
		System.out.println("Welcome to Blackjack!");

		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();

		Deck playerCards = new Deck();
		double playerMoney = 100.0;

		Deck dealerCards = new Deck();

		while (playerMoney > 0) {
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			boolean endRound = false;
			if (playerBet > playerMoney) {
				System.out.println("You cannot bet more than you have.");
				break;
			}

			System.out.println("Dealing Cards...");
			// player gets two cards
			playerCards.drawTopCard(playingDeck);
			playerCards.drawTopCard(playingDeck);

			// Dealer gets two cards
			dealerCards.drawTopCard(playingDeck);
			dealerCards.drawTopCard(playingDeck);

			while (true) {
				// Display player cards
				System.out.println("Your Hand:" + playerCards.toString());
				// Display Value
				System.out.println("Your hand is currently valued at: " + playerCards.getCardsValue());
				// Display dealer cards
				System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " and [hidden]");
				// What do they want to do
				System.out.println("Would you like to (1)Hit or (2)Stand");

				// They hit
				if (userInput == 1) {
					playerCards.drawTopCard(playingDeck);
					System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString());
					// Bust if they go over 21
					if (playerCards.getCardsValue() > 21) {
						System.out.println("Bust. Currently valued at: " + playerCards.getCardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				// Stand
				if (userInput == 2) {
					break;
				}

			}

			// Reveal Dealer Cards
			System.out.println("Dealer Cards:" + dealerCards.toString());
			// See if dealer has more points than player
			if ((dealerCards.getCardsValue() > playerCards.getCardsValue()) && endRound == false) {
				System.out.println(
						"Dealer beats you " + dealerCards.getCardsValue() + " to " + playerCards.getCardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}
			// Dealer hits at 16 stands at 17
			while ((dealerCards.getCardsValue() < 17) && endRound == false) {
				dealerCards.drawTopCard(playingDeck);
				System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
			}
			// Display value of dealer
			System.out.println("Dealers hand value: " + dealerCards.getCardsValue());
			// Determine if dealer busted
			if ((dealerCards.getCardsValue() > 21) && endRound == false) {
				System.out.println("Dealer Busts. You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			// Determine if Draw
			if ((dealerCards.getCardsValue() == playerCards.getCardsValue()) && endRound == false) {
				System.out.println("Draw.");
				endRound = true;
			}
			// Determine if player wins
			if ((playerCards.getCardsValue() > dealerCards.getCardsValue()) && endRound == false) {
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			} else if (endRound == false) // dealer wins
			{
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			// End of hand - put cards back in deck
			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			System.out.println("End of Hand.");

		}
		// Game is over
		System.out.println("Game over! You lost all your money. :(");
	}

}
