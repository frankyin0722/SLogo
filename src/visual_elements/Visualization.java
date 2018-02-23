package visual_elements;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Visualization {
	
	public static final int INITIAL_SCENE_WIDTH = 1000;
	public static final int INITIAL_SCENE_HEIGHT = 600;
	
	private Scene myScene;
	private BorderPane myPane;
	/**
	 * @author elizabethshulman
	 * @author xlany
	 * @param scene
	 * 
	 * Initializes and arranges each of the elements within the scene
	 * 
	 */

	public Visualization() {
		myPane = new BorderPane();
		initializeVis();
	}

	private void initializeVis() {
		myPane.setCenter(new DrawingWindow());
		myPane.setBottom(new TextFieldInput());
		myPane.setRight(new ControlPanelRight());
		myPane.setLeft(new ControlPanelLeft());
		
		myScene = new Scene(myPane,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
	}
	
	public Scene getScene() {
		return myScene;
	}
}
