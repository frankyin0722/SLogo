package visual_elements;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Visualization {
	public static final int INITIAL_SCENE_WIDTH = 800;
	public static final int INITIAL_SCENE_HEIGHT = 800;

	
	public Stage myStage;
	private Group myRoot;
	private Scene myScene;
	public Visualization(Stage stage) {
		myStage = stage;
		myScene = setupScene();
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private Scene setupScene() {
		myRoot = new Group();
		Scene scene = new Scene(myRoot,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
		return scene;
	}
	
}
