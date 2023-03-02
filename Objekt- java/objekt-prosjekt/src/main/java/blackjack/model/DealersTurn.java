package blackjack.model;

/*For å kunne teste funksjonaliteten til BlackjackGame.dealersTurn har jeg vært avhengig av å kunne bestemme
 * Card-objektene som ligger i playersHand, og dealersHand på forhånd.
 *  I BlackjackGame har dette ikke vært mulig, da playersHand og dealersHand kun kan inneholde tilfeldig
 *  utdelte kort fra et cardDeck-objektet.
 */

public class DealersTurn{
	
	private BlackjackHand playersHand;
	private BlackjackHand dealersHand;
	private Wallet playersWallet;
	public final GameStatisticsManager statisticsManager;
	private double playersBet;
	private double playersReturn;
	
	
	public DealersTurn() {
		playersHand=new BlackjackHand();
		dealersHand=new BlackjackHand();
		playersBet=10;
		playersWallet=new Wallet(100);
		statisticsManager=new WinStatistics("TestStatistics.txt");
	}
	
	public void setPlayersHand(String[] cardList) {
		for (String cardString:cardList) {
			Card card=new Card(cardString.charAt(0),Integer.valueOf(cardString.substring(1)));
			playersHand.addToHand(card);
		}
	}
	
	public void setDealersHand(String[] cardList) {
		for (String cardString:cardList) {
			Card card=new Card(cardString.charAt(0),Integer.valueOf(cardString.substring(1)));
			dealersHand.addToHand(card);
		}
	}
	
	public void clearPlayersHand() {
		playersHand.clearHand();
	}
	
	public void clearDealersHand() {
		dealersHand.clearHand();
	}
	
	public double getPlayersWallet() {
		return playersWallet.getWallet();
	}
	
	public BlackjackHand getPlayersHand() {
		return playersHand;
	}
	public BlackjackHand getDealersHand() {
		return dealersHand;
	}
	
	
	
	

	public String dealersTurn() {
		String status="";
		String result;
		if(playersHand.isBusted()) {
			status=("You are busted, house wins");
			result="l";
		}
		else {
			if(playersHand.isNatural()&&!dealersHand.isNatural()) {
				playersReturn=playersBet*2.5;
				playersWallet.deposit(playersReturn);
				status=("Congratulations on your natural, \nyou won : "+playersReturn);
				result="w";
			}else if(dealersHand.isBusted()) {
				playersReturn=2*playersBet;
				playersWallet.deposit(playersReturn);
				status= ("House is busted!\nCongratulations, you won : "+playersReturn);
				result="w";
			}
			else if(dealersHand.calculateValueOfHand()<playersHand.calculateValueOfHand()) {
				playersReturn=2*playersBet;
				playersWallet.deposit(playersReturn);
				status= ("Congratulations, you won : "+playersReturn);
				result="w";
			}
			else if(dealersHand.calculateValueOfHand()==playersHand.calculateValueOfHand()) {
				playersReturn=playersBet;
				status=("It is draw");
				result="d";
				playersWallet.deposit(playersReturn);
			}
			else {
				status=(dealersHand +" beats "+ playersHand);
				result="l";
			}
		}
		statisticsManager.addRound( result);
		return status;
	}
	
	

}