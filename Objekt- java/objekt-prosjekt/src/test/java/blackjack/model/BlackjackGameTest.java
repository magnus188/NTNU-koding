package blackjack.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class BlackjackGameTest{
	BlackjackGame blackjackGame;
	
	
	
	@BeforeEach
	public void setUp() { 
		blackjackGame=new BlackjackGame(); 
	}
	
	@Test
	@DisplayName("Test that the game is initialized correctly")
	public void TestConstructor() {
		assertFalse(blackjackGame.isPlayersTurn());
		assertFalse(blackjackGame.isBetPlaced());
		assertEquals(blackjackGame.getPlayersWallet(), 100);
		assertFalse(blackjackGame.isGameOver());		
	}
	
	@Test
	@DisplayName("Test that the bet is placed correctly")
	public void TestPlaceBet() {
		blackjackGame.placeBet(60);
		assertTrue(blackjackGame.isBetPlaced());
		assertEquals(blackjackGame.getPlayersBet(),60);
		assertEquals(blackjackGame.getPlayersWallet(),40);
		assertThrows(IllegalStateException.class, ()-> {//Bet ikke kan plasseres flere ganger p� en runde
			blackjackGame.placeBet(1);
	});
		setUp();
		assertEquals(blackjackGame.getPlayersWallet(),100);
		assertThrows(IllegalStateException.class, ()-> {
				blackjackGame.placeBet(120);
		});
		assertThrows(IllegalArgumentException.class, ()-> {
			blackjackGame.placeBet(-120);
	});
		assertThrows(IllegalArgumentException.class, ()-> {
			blackjackGame.placeBet(0);
	});
		assertFalse(blackjackGame.isBetPlaced());
		
		
		
	}
	
	@Test
	@DisplayName("Test that the players hand is set correctly")
	public void TestStartRound() {
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst
			blackjackGame.startRound();
	});
		blackjackGame.placeBet(10);
		blackjackGame.startRound();
		assertEquals(blackjackGame.getPlayersHand().getHand().size(),2);
		assertEquals(blackjackGame.getDealersHand().getHand().size(),1);
		assertTrue(blackjackGame.isPlayersTurn());		
		assertThrows(IllegalStateException.class, ()-> {//Runde kan ikke startes flere ganger p� en runde
			blackjackGame.startRound();
	});
	}
	
	@Test
	@DisplayName("Test that 'hit' is working correctly")
	public void TestHitPlayersHand() {
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.hitPlayersHand();
	});
		blackjackGame.placeBet(10);
		assertFalse(blackjackGame.isPlayersTurn());
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.stand();
	});
		blackjackGame.startRound();
		blackjackGame.hitPlayersHand();
		assertEquals(blackjackGame.getPlayersHand().getHand().size(),3);
		if(blackjackGame.getPlayersHand().calculateValueOfHand()>21) {
			assertFalse(blackjackGame.isPlayersTurn());
		}
	}
	
	@Test
	@DisplayName("Test that 'stand' is working correctly")
	public void TestStand() {
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.stand();
	});
		blackjackGame.placeBet(10);
		assertFalse(blackjackGame.isPlayersTurn());
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.stand();
	});
		blackjackGame.startRound();
		blackjackGame.stand();
		assertEquals(blackjackGame.getPlayersHand().getHand().size(),2);
		assertEquals(blackjackGame.getDealersHand().getHand().size(),1);
		assertFalse(blackjackGame.isPlayersTurn());
	}
	
	@Test
	@DisplayName("Test that 'double' is working correctly")
	public void TestDoubleHand() {
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.doubleHand();;
	});
		blackjackGame.placeBet(10);
		assertFalse(blackjackGame.isPlayersTurn());
		assertThrows(IllegalStateException.class, ()-> {//Bet m� plasseres f�rst, og runde m� starte
			blackjackGame.doubleHand();
	});
		blackjackGame.startRound();
		blackjackGame.doubleHand();
		assertEquals(blackjackGame.getPlayersHand().getHand().size(),3);
		assertEquals(blackjackGame.getDealersHand().getHand().size(),1);
		assertEquals(blackjackGame.getPlayersWallet(),80);
		assertEquals(blackjackGame.getPlayersBet(),20);
		assertFalse(blackjackGame.isPlayersTurn());
	}
	
	
	
	
	
	
	
}