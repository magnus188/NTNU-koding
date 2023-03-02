package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class BlackjackHand {
	private List<Card> blackjackHand;
	

	public BlackjackHand() {
		blackjackHand=new ArrayList<Card>();
	}
	
	public BlackjackHand(BlackjackHand hand) {
		blackjackHand=new ArrayList<Card>();
		for(Card card : hand.getHand()) {
			blackjackHand.add(card);
		}
	}
	
	
	public void addToHand(Card cardToAdd) {
		blackjackHand.add(cardToAdd);
	}
	
	public List<Card> getHand() {
		return new ArrayList<Card>(blackjackHand);
	}
	
	public Card getCardAt(int i) {
		if(i<blackjackHand.size()&&i>=0)
			return blackjackHand.get(i);
		throw new IllegalArgumentException("Index out of bound");
	}
	
	public int getFaceOfCardAt(int i) {
		return blackjackHand.get(i).getFace();
	}
	
	public boolean isBusted() {
		if(this.calculateValueOfHand()>21) {
			return true;
		}
		return false;
	}
	
	
	public int calculateValueOfHand() {
		int valueOfHand=0;
		if(blackjackHand.equals(null)) {
			return valueOfHand;
		}
		for(Card card:blackjackHand) {
			if(card.getFace()==11||card.getFace()==12||card.getFace()==13) {
				valueOfHand+=10;
			}else if(card.getFace()==1){
				valueOfHand+=11;
			}
			else {
				valueOfHand+=card.getFace();
			}
		}
		if (valueOfHand>21) {
			for (Card card:blackjackHand) {
				if(card.getFace()==1) {
					valueOfHand-=10;
					if(valueOfHand<=21)
						break;
				}
			}
		}
		if (valueOfHand<21&&blackjackHand.size()>=5) {
			valueOfHand=21;
		}
		return valueOfHand;
	}
	
	public boolean isNatural() {
		if (this.calculateValueOfHand()==21&&blackjackHand.size()==2) {
			return true;
		}
		return false;
	}
	
	public void clearHand() {
		if (!blackjackHand.isEmpty())
			this.blackjackHand.clear();
		else
			throw new IllegalStateException("Hand is empty");
	}
	

	
	@Override
	public String toString() {
		String str="";
		if(blackjackHand.size()==1) {
			str+=blackjackHand.get(0);
			return str;
		}
		else if(blackjackHand.size()==0) {
			return str;
		}
		for (int i=0;i<blackjackHand.size()-1;i++) {
			str+=blackjackHand.get(i)+", ";
		}
		str+=blackjackHand.get(blackjackHand.size()-1);
		return str;
	}
	
	public static void main(String[] args) {
		 BlackjackHand hand=new BlackjackHand();
		 hand.clearHand();
		 System.out.println(hand);
		 System.out.println("e");
	}
	
}