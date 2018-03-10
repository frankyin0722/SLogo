package slogo_team08;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author elizabethshulman
 * 
 * This class launches the IDE.
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Engine(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
