package slogo_team08;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.TabView;
public class Engine {

	public Stage myStage;
	public Group myRoot;
	private Scene myScene;
	private TabView slogoView;
	

	
	public void initializeSimulation(Stage primaryStage) {
		myStage = primaryStage;

		slogoView = new TabView();
		myScene = slogoView.getScene();
		myStage.setScene(myScene);
//		myStage.setMaximized(true);
//		myStage.setFullScreen(true);
		
		myStage.show();
	}
}
