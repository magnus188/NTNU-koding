package oving3;

import java.util.ArrayList;

public class CardDeck {

    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private char[] cardColors = { 'S', 'H', 'D', 'C' };

    public CardDeck(int n) {

        if (n < 0 || n > 13) {
            throw new IllegalArgumentException("Invalid value");
        }

        for (int i = 0; i <= 3; i++) {
            // Create a new deck
            for (int j = 1; j <= n; j++) {
                cardDeck.add(new Card(cardColors[i], j));
            }
        }
    }

    public int getCardCount() {
        return cardDeck.size();
    }

    public Card getCard(int index) {
        try {
            return cardDeck.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    public void shufflePerfectly() {
        // split list into two halfs
        // for i in range
        // partal fra liste 1
        // oddetall fra liste 2

        //split
        Card[] firstHalf = cardDeck.subList(0, cardDeck.size() / 2).toArray(new Card[0]);
        Card[] secondHalf = cardDeck.subList(cardDeck.size() / 2, cardDeck.size()).toArray(new Card[0]);

        // shuffle
        for (int i = 0; i < cardDeck.size(); i++) {
            if (i % 2 == 0) {
                cardDeck.set(i, firstHalf[i/2]);
            }
            else {
                cardDeck.set(i, secondHalf[i/2]);
            }
        }
        
        System.out.println("hello");
    }

    public static void main(String[] args) {
        CardDeck cardstokk = new CardDeck(2);
        cardstokk.shufflePerfectly();

        //System.out.println(cardstokk.getCardCount());  
    }

}
