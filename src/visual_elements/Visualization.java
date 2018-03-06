package visual_elements;




import java.util.ArrayList;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.geometry.Insets;
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
public class Visualization extends BorderPane {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	
	private ResourceBundle myResources;
	private ScrollingDrawingWindow myScrollingDrawingWindow;
	private CommandTreeInterpreter interpreter;
	private ControlTextInput myControlTextInput;
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;
	private Turtle myDefaultTurtle;
	
	public Visualization() {
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
		this.setPadding(new Insets(20,20,20,20));
//		this.setTop(new InfoTop());
		this.setCenter(myScrollingDrawingWindow);
		this.setBottom(myControlTextInput);
		this.setRight(myControlPanelRight);
		this.setLeft(myControlPanelLeft);
		this.setWidth(Double.MAX_VALUE);
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
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
}
