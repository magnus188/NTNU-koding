package oving3;

public class Card {
    
    private char cardColor;
    private int cardValue;
    
    public Card(char color, int value) {
        if (String.valueOf(color).matches("[SHDC]") && value >= 1 && value <= 13) {
            this.cardValue = value;
            this.cardColor = color;
        } else {
            throw new IllegalArgumentException("Invalid values");
        }

    }

    public char getSuit() {
        return cardColor;
    }

    public int getFace() {
        return cardValue;
    }

    public String toString() {
        return cardColor + String.valueOf(cardValue);
    }

}
