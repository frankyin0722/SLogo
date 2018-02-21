package slogo_team08;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author benhubsch
 * @author elizabethshulman
 * @author andrewyeung
 * 
 * This class launches the IDE.
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Engine e = new Engine();
		e.initializeSimulation(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
