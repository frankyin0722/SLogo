package slogo_team08;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import visual_elements.Visualization;
public class Engine {

	public Stage myStage;
	public Group myRoot;
	private Scene myScene;
	private Visualization myVis;
	

	
	public void initializeSimulation(Stage primaryStage) {
		myStage = primaryStage;
		myVis = new Visualization();
		myScene = myVis.getScene();
		
		myStage.setScene(myScene);
		myStage.setMaximized(true);
		myStage.setFullScreen(true);
		
		myStage.show();
	}
}
