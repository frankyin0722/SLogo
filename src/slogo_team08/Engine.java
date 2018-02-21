package slogo_team08;

import javafx.stage.Stage;
import visual_elements.Visualization;
public class Engine {

	Stage myStage;
	
	public void initializeSimulation(Stage primaryStage) {
		myStage = primaryStage;
		new Visualization(myStage);
	}
	
}
