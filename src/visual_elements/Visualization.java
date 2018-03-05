package visual_elements;




import java.util.ArrayList;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
	public static final String DEFAULT_LANGUAGE = "English";
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	
	private Scene myScene;
	private BorderPane myPane;
	private ResourceBundle myResources;
	private ScrollingDrawingWindow myScrollingDrawingWindow;
	private CommandTreeInterpreter interpreter;
	private ControlTextInput myControlTextInput;
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;
	private Turtle myDefaultTurtle;
	
	public Visualization() {
		myPane = new BorderPane();
		initializeAll();
		initializeLayout();
	}
	
	private void initializeAll() {
		setLanguage(DEFAULT_LANGUAGE);
		myScrollingDrawingWindow = new ScrollingDrawingWindow();
		myDefaultTurtle = myScrollingDrawingWindow.getDefaultTurtle();
		interpreter = new CommandTreeInterpreter(new ArrayList<Turtle>() {{
			add(myDefaultTurtle);
		}});
		myControlPanelRight = new ControlPanelRight(interpreter, myResources);		
		myControlTextInput = new ControlTextInput(interpreter, this);
		myControlPanelLeft = new ControlPanelLeft(interpreter, myDefaultTurtle, myResources);
	}
	
	private void initializeLayout() {		
		myPane.setPadding(new Insets(20,20,20,20));
		myPane.setTop(new InfoTop());
		myPane.setCenter(myScrollingDrawingWindow);
		myPane.setBottom(myControlTextInput);
		myPane.setRight(myControlPanelRight);
		myPane.setLeft(myControlPanelLeft);
		myPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		
		myScene = new Scene(myPane,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
	}
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	public void changeLanguage() {
		myResources = myControlPanelRight.getLanguage();
	}
	
	public ResourceBundle getLanguage() {
		changeLanguage();
		return myResources;
	}
	
	public Scene getScene() {
		return myScene;
	}
}
