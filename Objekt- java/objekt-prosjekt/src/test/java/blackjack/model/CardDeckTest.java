package blackjack.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CardDeckTest{
	final String stringDeck1="1S, 2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, 11S, 12S, 13S, "
			+ "1H, 2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, 11H, 12H, 13H, "
			+ "1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, 11D, 12D, 13D, "
			+ "1C, 2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, 11C, 12C, 13C";
	
	final String stringDeck2="1S, 2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, 11S, 12S, 13S, "
			+ "1H, 2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, 11H, 12H, 13H, "
			+ "1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, 11D, 12D, 13D, "
			+ "1C, 2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, 11C, 12C, 13C, "
			+ "1S, 2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, 11S, 12S, 13S, "
			+ "1H, 2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, 11H, 12H, 13H, "
			+ "1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, 11D, 12D, 13D, "
			+ "1C, 2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, 11C, 12C, 13C";
	
	CardDeck deck;
	CardDeck deck1;
	CardDeck deck2;
	Collection<Card> expected1;
	Collection<Card> expected2;
	
	@BeforeEach
	public void setUp() {
		expected1=new ArrayList<Card>();
		expected2=new ArrayList<Card>();
		for(String s:stringDeck1.split(", ")) {
			if(s.length()==2) {
				char suit=s.charAt(1);
				int face=Character.getNumericValue(s.charAt(0));	
				expected1.add(new Card(suit,face));
			}else {
				char suit=s.charAt(2);
				int face=10+Character.getNumericValue(s.charAt(1));
				expected1.add(new Card(suit,face));
			}
		}
		for(String s:stringDeck2.split(", ")) {
			if(s.length()==2) {
				char suit=s.charAt(1);
				int face=Character.getNumericValue(s.charAt(0));	
				expected2.add(new Card(suit,face));
			}else {
				char suit=s.charAt(2);
				int face=10+Character.getNumericValue(s.charAt(1));
				expected2.add(new Card(suit,face));
			}
		}
		deck1=new CardDeck();
		deck2=new CardDeck(2);
	}
	
	@Test
	public void TestConstructor() {
		assertThrows(IllegalArgumentException.class,() -> {
			deck=new CardDeck(9);
		});
		assertThrows(IllegalArgumentException.class,() -> {
			deck=new CardDeck(0);
		});
	}
	
	
	@Test
	public void TestGetCardDeck() {
		assertEquals(expected1.size(), deck1.getCardDeck().size());
		Iterator<Card> it1=expected1.iterator();
		for (int i=0;i<deck1.getCardDeck().size();i++) {
			Card expectedCard=it1.next();
			Card actualCard=deck1.getCardDeck().get(i);
			assertEquals(expectedCard.getFace(), actualCard.getFace());
			assertEquals(expectedCard.getSuit(), actualCard.getSuit());

		}
		assertEquals(expected2.size(), deck2.getCardDeck().size());
		Iterator<Card> it2=expected2.iterator();
		for (int i=0;i<deck2.getCardDeck().size();i++) {
			Card expectedCard=it2.next();
			Card actualCard=deck2.getCardDeck().get(i);
			assertEquals(expectedCard.getFace(), actualCard.getFace());
			assertEquals(expectedCard.getSuit(), actualCard.getSuit());
		}
	}
	
	
	
/*
 * 	Her stokkes deck1, før det sjekkes om samtlige av kortene i deck1 ligger i samme rekkefølge som det de gjorde før
 *  de ble stokket (Herav expected1.iterator). Sannsynligheten for at dette skjer dersom kortstokken
 *  stokkes helt tilfeldig er 1:(52!). 
 * 	For å minimere sannsynligheten for at dette skjer ytterligere, velger jeg å gjenta denne prossessen 10 ganger.
 * 	Sannsynligheten for at if-setningen nederst slår ut ved en tilfeldighet er nå 1:((52!)**10)
 */
	@Test
	public void TestShuffle() {
		int n=0;
		for(int j=0;j<10;j++) {
			Iterator<Card> it1=expected1.iterator();
			deck1.shuffleCardDeck();
			for (int i=0;i<deck1.getCardDeck().size();i++) {
				Card expectedCard=it1.next();
				Card actualCard=deck1.getCardDeck().get(i);
				if(expectedCard.getFace()==actualCard.getFace()&&
						expectedCard.getSuit()==actualCard.getSuit())
						n++;
			}
		}
		assertTrue(deck1.hasBeenShuffled());		
		if(n==10*52) { 
			throw new Error("File has not been shuffled");
		}
	}
	
	@Test
	public void TestDrawFromCardDeck() {
		for(int i=0;i<52;i++) {
			Card card=deck1.drawFromCardDeck();
			assertEquals(deck1.getCardDeck().indexOf(card),-1);
			assertTrue(deck1.hasBeenDistributed());
		}
		assertThrows(IllegalStateException.class,() -> {
			deck1.drawFromCardDeck();
		});
	}
	
	
	
	
}