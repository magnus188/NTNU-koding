package blackjack.fxui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class blackjackApp extends Application {

	@Override 
	public void start(Stage primaryStage) throws Exception {
		Parent parent= FXMLLoader.load(getClass().getResource("BlackjackGame.fxml"));
		primaryStage.setTitle("Blackjack");
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(blackjackApp.class );
	}
}
