package visual_elements;




import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Visualization {
	
	public static final int INITIAL_SCENE_WIDTH = 1000;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	
	private Scene myScene;
	private BorderPane myPane;
//	private DrawingWindow myDrawingWindow;
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
//		myDrawingWindow = new DrawingWindow();
		
		myPane.setPadding(new Insets(20,20,20,20));
//		myPane.setMargin(myPane.getCenter(), new Insets(12,12,12,12));
		myPane.setTop(new InfoTop());
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
