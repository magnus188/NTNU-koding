package blackjack.model;

public class BlackjackGame {
	private BlackjackHand playersHand;
	private BlackjackHand dealersHand;
	private CardDeck cardDeck;
	private Wallet playersWallet;
	public final GameStatisticsManager statisticsManager;
	private double playersBet;
	private double playersReturn;
	private boolean playersTurn;
	private boolean betIsPlaced;

	public BlackjackGame() {
		statisticsManager = new WinStatistics();
		playersWallet = new Wallet(100);
		cardDeck = new CardDeck(6);
		betIsPlaced = false;
	}

	public void placeBet(int bet) {
		if (betIsPlaced)
			throw new IllegalStateException("Bet is already placed");
		try {
			playersWallet.withdraw(bet);
			this.playersBet = bet;
			betIsPlaced = true;
		} catch (Exception e) {
			throw e;
		}
	}

	public void startRound() {
		if (playersTurn || !betIsPlaced)
			throw new IllegalStateException("Bet must be place before round can start");
		if (cardDeck.getCardDeck().size() < 10)
			cardDeck = new CardDeck(6);
		playersHand = new BlackjackHand();
		dealersHand = new BlackjackHand();
		cardDeck.shuffleCardDeck();
		playersTurn = true;
		for (int i = 0; i < 2; i++) {
			hit(playersHand);
		}
		hit(dealersHand);
	}

	public void doubleHand() {
		if (!(playersTurn && betIsPlaced)) {
			throw new IllegalStateException("Round must have started first");
		}
		try {
			playersWallet.withdraw(playersBet);
			hitPlayersHand();
			playersBet *= 2;
			playersTurn = false;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("You don't have enough money to double");
		}
	}

	public void stand() {
		if (!(playersTurn && betIsPlaced)) {
			throw new IllegalStateException("Round must have started first");
		}
		playersTurn = false;
	}

	public void hitPlayersHand() {
		if (!(playersTurn && betIsPlaced)) {
			throw new IllegalStateException("Round must have started first");
		} hit(playersHand);
		if (playersHand.calculateValueOfHand() >= 21) {
			playersTurn = false;
		}
	}

	public String dealersTurn() {
		if (playersTurn) {
			throw new IllegalStateException("Cannot invoke dealersTurn while playesTurn==true");
		}
		String status = "";
		String result;
		if (playersHand.isBusted()) {
			status = ("You are busted, house wins");
			result = "l";
		} else {
			while (dealersHand.calculateValueOfHand() < 17) {
				hit(dealersHand);
			}
			if (playersHand.isNatural() && !dealersHand.isNatural()) {
				playersReturn = playersBet * 2.5;
				playersWallet.deposit(playersReturn);
				status = ("Congratulations on your natural,\nyou won : " + playersReturn);
				result = "w";
			} else if (dealersHand.calculateValueOfHand() < playersHand.calculateValueOfHand()) {
				playersReturn = 2 * playersBet;
				playersWallet.deposit(playersReturn);
				status = ("Congratulations, you won : " + playersReturn);
				result = "w";
			} else if (dealersHand.isBusted()) {
				playersReturn = 2 * playersBet;
				playersWallet.deposit(playersReturn);
				status = ("House is busted!\nCongratulations, you won : " + playersReturn);
				result = "w";
			} else if (dealersHand.calculateValueOfHand() == playersHand.calculateValueOfHand()
					&& !dealersHand.isNatural()) {
				playersReturn = playersBet;
				playersWallet.deposit(playersReturn);
				status = ("It is draw");
				result = "d";
			} else {
				status = (dealersHand + " beats " + playersHand);
				result = "l";
			}
		}
		betIsPlaced = false;
		statisticsManager.addRound(result);
		return status;
	}

	public boolean isGameOver() {
		if (playersWallet.getWallet() < 5)
			return true;
		return false;

	}

	public boolean isPlayersTurn() {
		return playersTurn;
	}

	public boolean isBetPlaced() {
		return betIsPlaced;
	}

	public BlackjackHand getPlayersHand() {
		return new BlackjackHand(playersHand);
	}

	public BlackjackHand getDealersHand() {
		return new BlackjackHand(dealersHand);
	}

	public double getPlayersWallet() {
		return playersWallet.getWallet();
	}

	public double getPlayersBet() {
		return playersBet;
	}

	private void hit(BlackjackHand handToHit) {
		handToHit.addToHand(cardDeck.drawFromCardDeck());
	}

}
