package com.chagu.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.chagu.blackjack.Deck;
import com.chagu.blackjack.Game;

@SpringBootTest
class BlackjackApplicationTests {

	@Test
	void testDeckSize() {
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		assertEquals(52, playingDeck.deckSize(), "Test that initial deck size is 52 !!!");
	}

	@Test
	void playWithHit() {
		Game game = new Game();
		game.playBlackJack(50, 1);
	}
	
	@Test
	void playWithStand() {
		Game game = new Game();
		game.playBlackJack(50, 2);
	}

}
