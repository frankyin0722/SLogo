package visual_elements;




import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import turtle.Turtle;

/**
 * @author elizabethshulman
 * @author xlany
 * @param scene
 * 
 * Initializes and arranges each of the elements within the scene
 * 
 */
public class Visualization {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final int INITIAL_SCENE_WIDTH = 1000;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	
	private Scene myScene;
	private BorderPane myPane;
	private ResourceBundle myResources;
	private DrawingWindow myDrawingWindow;
	private ControlTextInput myControlTextInput;
	
	private Turtle myDefaultTurtle;
	public Visualization() {
		myPane = new BorderPane();
		setupLanguage("English");
		initializeAll();
		initializeVis();
	}
	
	private void initializeAll() {
		myDrawingWindow = new DrawingWindow();
		myDefaultTurtle = myDrawingWindow.getDefaultTurtle();
		myControlTextInput = new ControlTextInput(myDefaultTurtle);
	}

	private void initializeVis() {		
		myPane.setPadding(new Insets(20,20,20,20));
		myPane.setTop(new InfoTop());
		myPane.setCenter(myDrawingWindow);
		myPane.setBottom(myControlTextInput);
		myPane.setRight(new ControlPanelRight());
		myPane.setLeft(new ControlPanelLeft(myResources));
		
//		DrawingWindow drawingWindow = new DrawingWindow();
//		myPane.setCenter(new DrawingWindow());
//		myPane.setBottom(new ControlTextInput());
//		myPane.setRight(new ControlPanelRight());
//		myPane.setLeft(new ControlPanelLeft(myResources));
		
		myScene = new Scene(myPane,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
	}
	
	private void setupLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
	}
	
	public Scene getScene() {
		return myScene;
	}
}
