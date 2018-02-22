package slogo_team08;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import visual_elements.Visualization;
public class Engine {

	public Stage myStage;
	public Group myRoot;
	private Scene myScene;
	
	public static final int INITIAL_SCENE_WIDTH = 650;
	public static final int INITIAL_SCENE_HEIGHT = 492;
	
	public void initializeSimulation(Stage primaryStage) {
		myStage = primaryStage;
		myScene = setupScene();
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private Scene setupScene() {
		myRoot = new Group();
		Scene scene = new Scene(myRoot,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
		new Visualization(myRoot);
		return scene;
	}
}
