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
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;
	private Turtle myDefaultTurtle;
	public Visualization() {
		myPane = new BorderPane();
		setupLanguage("English");
		initializeAll();
		initializeLayout();
	}
	
	private void initializeAll() {
		myDrawingWindow = new DrawingWindow();
		myDefaultTurtle = myDrawingWindow.getDefaultTurtle();
		
		myControlTextInput = new ControlTextInput(myDefaultTurtle);
		myControlPanelRight = new ControlPanelRight();
		myControlPanelLeft = new ControlPanelLeft(myDefaultTurtle, myResources);
	}

	private void initializeLayout() {		
		myPane.setPadding(new Insets(20,20,20,20));
		myPane.setTop(new InfoTop());
		myPane.setCenter(myDrawingWindow);
		myPane.setBottom(myControlTextInput);
		myPane.setRight(myControlPanelRight);
		myPane.setLeft(myControlPanelLeft);
		
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
