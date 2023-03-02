package blackjack.fxui;

import java.io.IOException;


import blackjack.model.BlackjackGame;
import blackjack.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class blackjackController {
	private BlackjackGame blackjackGame;

	@FXML
	private Label winsGames;

	@FXML
	private Button betButton, hitButton, standButton, doubleButton, quitButton, playAgainButton, clearStatistics;

	@FXML
	private TextField betInput, gameOverStatus, walletTextField, winStatistics;



	@FXML
	private FlowPane playersCards, dealersCards;


	@FXML
	private void initialize() {
		this.blackjackGame = new BlackjackGame();
		drawNewRound();
	}

	@FXML
	private void handleBet() {
		try {
			int betValue = Integer.valueOf(betInput.getText());
			blackjackGame.placeBet(betValue);
			blackjackGame.startRound();
			drawPlayersTurn();
		} catch (Exception e) {
			String str;
			str = e.toString();
			gameOverStatus.setVisible(true);
			gameOverStatus.clear();
			gameOverStatus.appendText(str);
		}
	}

	@FXML
	private void handleHit() {
		blackjackGame.hitPlayersHand();
		if (blackjackGame.isPlayersTurn()) {
			drawPlayersTurn();
		} else {
			drawDealersTurn();
		}
	}

	@FXML
	private void handleDouble() {
		try {
			blackjackGame.doubleHand();
			drawBetInput();
			drawWallet();
			drawDealersTurn();
		} catch (IllegalStateException e) {
			String str;
			str = e.toString();
			gameOverStatus.setVisible(true);
			gameOverStatus.clear();
			gameOverStatus.appendText(str);
		}
	}

	@FXML
	private void handleStand() {
		blackjackGame.stand();
		drawDealersTurn();
	}

	@FXML
	private void handleQuit() {
		drawQuit();
	}

	@FXML
	private void handlePlayAgain() {
		drawNewRound();
	}

	@FXML
	private void handleClearStatistics() {
		blackjackGame.statisticsManager.clearStatistics();
		drawStatistics();
	}



	@FXML
	private void drawNewRound() {
		walletTextField.setDisable(true);
		quitButton.setDisable(true);
		playAgainButton.setDisable(true);
		quitButton.setVisible(false);
		playAgainButton.setVisible(false);
		betButton.setVisible(true);
		betButton.setDisable(false);
		betInput.setVisible(true);
		betInput.setDisable(false);
		hitButton.setDisable(true);
		hitButton.setVisible(false);
		doubleButton.setDisable(true);
		doubleButton.setVisible(false);
		standButton.setDisable(true);
		standButton.setVisible(false);
		gameOverStatus.setVisible(false);
		dealersCards.getChildren().clear();
		playersCards.getChildren().clear();
		betInput.clear();
		drawWallet();
		drawStatistics();

	}

	@FXML
	private void drawQuit() {
		quitButton.setVisible(false);
		quitButton.setDisable(true);
		playAgainButton.setVisible(false);
		playAgainButton.setDisable(true);
		gameOverStatus.clear();
		gameOverStatus.appendText("Game over");
	}

	@FXML
	private void drawPlayersTurn() {
		gameOverStatus.setVisible(false);
		hitButton.setDisable(false);
		hitButton.setVisible(true);
		doubleButton.setDisable(false);
		doubleButton.setVisible(true);
		standButton.setDisable(false);
		standButton.setVisible(true);
		betButton.setDisable(true);
		betInput.setDisable(true);
		drawCards();
		drawWallet();
	}
	
	

	@FXML
	private void drawDealersTurn() {
		hitButton.setDisable(true);
		hitButton.setVisible(false);
		doubleButton.setDisable(true);
		doubleButton.setVisible(false);
		standButton.setDisable(true);
		standButton.setVisible(false);
		quitButton.setDisable(false);
		quitButton.setVisible(true);
		playAgainButton.setDisable(false);
		playAgainButton.setVisible(true);
		gameOverStatus.setVisible(true);
		gameOverStatus.clear();
		String status = blackjackGame.dealersTurn();
		gameOverStatus.appendText(status);
		drawCards();
		drawWallet();
		drawStatistics();
		if(blackjackGame.isGameOver()) {
			drawQuit();
		}
	}

	private void drawWallet() {
		walletTextField.clear();
		walletTextField.appendText(String.valueOf(blackjackGame.getPlayersWallet()));
	}
	
	private void drawBetInput() {
		betInput.clear();
		betInput.appendText(String.valueOf(blackjackGame.getPlayersBet()));
	}
	
	private void drawStatistics() {
		winStatistics.clear();
		winStatistics.appendText(blackjackGame.statisticsManager.getStatistics());
	}

	@FXML
	private void drawCards() {
		dealersCards.getChildren().clear();
		playersCards.getChildren().clear();
		for (Card card : blackjackGame.getDealersHand().getHand()) {
			try {
				ImageView image = card.assignImage();
				image.setFitHeight(90);
				image.setFitWidth(60);
				dealersCards.getChildren().add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (Card card : blackjackGame.getPlayersHand().getHand()) {
			try {
				ImageView image = card.assignImage();
				image.setFitHeight(90);
				image.setFitWidth(60);
				playersCards.getChildren().add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (blackjackGame.isPlayersTurn()) {
			try {
				Card flippedCard = new Card();
				ImageView image = flippedCard.assignImage();
				image.setFitHeight(90);
				image.setFitWidth(60);
				dealersCards.getChildren().add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
