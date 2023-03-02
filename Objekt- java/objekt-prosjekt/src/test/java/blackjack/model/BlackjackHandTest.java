package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackHandTest {
	BlackjackHand hand;
	Card s1;
	Card h1;
	Card d1;
	Card c1;
	Card s2;
	Card h2;
	Card d2;
	Card c2;
	Card c8;
	Card c9;
	Card s10;
	Card s11;
	Card s12;
	Card s13;
	List<Card> expected;

	@BeforeEach
	public void setUp() {
		hand = new BlackjackHand();
		s1 = new Card('S', 1);
		s2 = new Card('S', 2);
		h1 = new Card('H', 1);
		h2 = new Card('H', 2);
		d1 = new Card('D', 1);
		d2 = new Card('D', 2);
		c1 = new Card('C', 1);
		c2 = new Card('C', 2);
		c8 = new Card('C', 8);
		c9 = new Card('C', 9);
		s10 = new Card('S', 10);
		s11 = new Card('S', 11);
		s12 = new Card('S', 12);
		s13 = new Card('S', 13);
		expected = new ArrayList<Card>();
		expected.add(c1);
		expected.add(s2);
	}

	@Test
	public void TestGetHand() {
		assertEquals(hand.getHand().size(), 0);
		hand.addToHand(c1);
		hand.addToHand(s2);
		assertEquals(expected.size(), hand.getHand().size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), hand.getHand().get(i));
		}
	}

	@Test
	public void TestGetCardAt() {
		hand.addToHand(c1);
		hand.addToHand(s2);
		for (int i = 0; i < hand.getHand().size(); i++) {
			assertEquals(hand.getCardAt(i), expected.get(i));
		}
		assertThrows(IllegalArgumentException.class, () -> {
			hand.getCardAt(2);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			hand.getCardAt(-1);
		});
	}

	@Test
	public void TestClearHand() {
		hand.addToHand(c1);
		hand.addToHand(c2);
		hand.clearHand();
		assertEquals(hand.getHand().size(), 0);
	}

	@Test
	@DisplayName("Test Ace valuation")
	public void TestSpecialAce() {
		hand.addToHand(s1);
		hand.addToHand(d1);
		hand.addToHand(c9);
		assertEquals(hand.calculateValueOfHand(), 21);
		hand.addToHand(s2);
		assertEquals(hand.calculateValueOfHand(), 13);
	}

	@Test
	public void TestCalculateValueOfHand() {
		hand.addToHand(c1);
		assertEquals(hand.calculateValueOfHand(), 11);
		hand.addToHand(c2);
		assertEquals(hand.calculateValueOfHand(), 13);
		hand.addToHand(s1);
		assertEquals(hand.calculateValueOfHand(), 14);
		hand.addToHand(s10);
		assertEquals(hand.calculateValueOfHand(), 14);
		hand.clearHand();
		hand.addToHand(s11);
		assertEquals(hand.calculateValueOfHand(), 10);
		hand.addToHand(s12);
		assertEquals(hand.calculateValueOfHand(), 20);
		hand.clearHand();
		hand.addToHand(s13);
		assertEquals(hand.calculateValueOfHand(), 10);
		hand.addToHand(c1);
		assertEquals(hand.calculateValueOfHand(), 21);
		hand.clearHand();
		hand.addToHand(c2);
		hand.addToHand(s2);
		hand.addToHand(d2);
		hand.addToHand(h2);
		hand.addToHand(s10);
		assertEquals(hand.calculateValueOfHand(), 21);
	}

	@Test
	void TestIsNatural() {
		hand.addToHand(c1);
		hand.addToHand(s10);
		assertTrue(hand.isNatural());
		hand.clearHand();
		hand.addToHand(c2);
		hand.addToHand(c8);
		hand.addToHand(c1);
		assertEquals(hand.calculateValueOfHand(), 21);
		assertFalse(hand.isNatural());
		hand.clearHand();
		hand.addToHand(c2);
		hand.addToHand(s2);
		hand.addToHand(c1);
		hand.addToHand(d1);
		hand.addToHand(d2);
		System.out.println(hand);
		assertEquals(hand.calculateValueOfHand(), 21);
		assertFalse(hand.isNatural());
	}

	@Test
	public void TestIsBusted() {
		hand.addToHand(c1);
		assertFalse(hand.isBusted());
		hand.addToHand(c2);
		assertFalse(hand.isBusted());
		hand.addToHand(s1);
		assertFalse(hand.isBusted());
		hand.addToHand(d1);
		assertFalse(hand.isBusted());
		hand.addToHand(h1);
		assertFalse(hand.isBusted());
		hand.addToHand(s10);
		assertFalse(hand.isBusted());
		hand.addToHand(s12);
		assertTrue(hand.isBusted());
		hand.clearHand();
		hand.addToHand(s13);
		assertFalse(hand.isBusted());
		hand.addToHand(c1);
		assertFalse(hand.isBusted());
		hand.addToHand(c2);
		assertFalse(hand.isBusted());
	}

}