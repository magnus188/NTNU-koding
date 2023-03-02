package blackjack.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest{
	Card card;
	Card card1;
	Card card2;
	
	
	@BeforeEach
	public void setup() {
		card1=new Card('S',1);
		card2=new Card('D',12);
	}
	
	
	
	@Test
	public void checkConstructor() {
		assertThrows(IllegalArgumentException.class,() -> {
			card=new Card('X',1);
		});
		assertThrows(IllegalArgumentException.class,() -> {
			card=new Card('X',0);
		});
		assertThrows(IllegalArgumentException.class,() -> {
			card=new Card('X',14);
		});
		assertThrows(IllegalArgumentException.class,() -> {
			card=new Card('S',0);
		});
	}
	
	@Test
	public void getFace() {
		assertEquals(card1.getFace(),1);
		assertEquals(card2.getFace(),12);
	}
	
	@Test
	public void getSuit() {
		assertEquals(card1.getSuit(),'S');
		assertEquals(card2.getSuit(),'D');
	}
	
	
	
}