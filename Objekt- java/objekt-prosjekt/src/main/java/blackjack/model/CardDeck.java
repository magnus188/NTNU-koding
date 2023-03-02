package blackjack.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CardDeck {
	private List<Card> cardDeck=new ArrayList<Card>();
	private int cardDeckSize;
	private boolean hasBeenShuffled=false;
	private boolean hasBeenDistributed=false;
	
	public CardDeck() {
		super();
		for(char suit:Card.validSuits) {
			for(int j=1;j<14;j++) {
				cardDeck.add(new Card(suit, j));				}
		}	
		cardDeckSize=cardDeck.size();
	}
	
	/**@param number of card decks wanted to use
	 */
	public CardDeck(int totalNumberOfCardDecks) {
		if(totalNumberOfCardDecks<1) {
			throw new IllegalArgumentException("You must have at least 1 card deck");
		}if(totalNumberOfCardDecks>8) {
			throw new IllegalArgumentException("You can't have more than 8 card deck's");
		}
		for(int i=0;i<totalNumberOfCardDecks;i++) {
			for(char suit:Card.validSuits) {
				for(int j=1;j<14;j++) {
					cardDeck.add(new Card(suit, j));
				}
			}
		}
		cardDeckSize=cardDeck.size();
	}
	
	
	
	
	public void shuffleCardDeck() {
		List<Card> shuffleCardDeck=new ArrayList<Card>(cardDeck);
		List<Card> shuffledCardDeck=new ArrayList<Card>();
		
		Random rand=new Random();
		for (int i=cardDeckSize-1;i>0;i--) {
			int r=rand.nextInt(i);
			shuffledCardDeck.add(shuffleCardDeck.get(r));
			shuffleCardDeck.remove(r);
		}
		shuffledCardDeck.add(rand.nextInt(cardDeckSize-2),shuffleCardDeck.get(0));
		int shuffledCardDeckSize=cardDeckSize;
		for (int i=0;i<shuffledCardDeckSize;i++) {
			cardDeck.add(shuffledCardDeck.get(i));
			cardDeck.remove(0);
		}
		hasBeenShuffled=true;  
	}
	
	public Card drawFromCardDeck() {
		if(cardDeckSize>0) {
			Card card=cardDeck.get(0);
			cardDeck.remove(0);
			hasBeenDistributed=true;
			cardDeckSize-=1;
			return card;
		}throw new IllegalStateException("No more cards to draw");
	}
	
	public List<Card> getCardDeck(){
		return new ArrayList<Card>(cardDeck);
	}
	
	public boolean hasBeenShuffled() {
		return hasBeenShuffled;
	}
	public boolean hasBeenDistributed() {
		return hasBeenDistributed;
	}
	
 
	
	
	@Override
	public String toString() {
		String str="";
		for (int i=0;i<(cardDeckSize/13)-1;i++) {
			for(int j=0;j<13;j++) {
				str+=cardDeck.get((i*13)+j)+", ";
			}str+="\n";
		}
		for(int i=cardDeckSize-13;i<cardDeckSize-1;i++) {
			str+=cardDeck.get(i)+", ";
		}
		str+=cardDeck.get(cardDeckSize-1)+".";
		return str;
	}
	
	public static void main(String[] args) {
		CardDeck deck=new CardDeck();
		System.out.println(deck);
		System.out.println("------------------");
		deck.shuffleCardDeck();
		System.out.println(deck);
	}
	
	
}