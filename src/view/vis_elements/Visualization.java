package view.vis_elements;




import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import slogo_team08.IConstants;
import turtle.TurtleController;
import view.canvas.ScrollingDrawingWindow;

/**
 * @author elizabethshulman
 * @author xlany
 * @param scene
 * 
 * Initializes and arranges each of the elements within the scene
 * 
 */
public class Visualization extends BorderPane implements IConstants {
	private ResourceBundle myResources;
	private ScrollingDrawingWindow myExternalCanvas;
	private TurtleController myTurtleController;
	private CommandTreeInterpreter interpreter;
	private ControlTextInput myControlTextInput;
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;	
	
	public Visualization() {
		initializeAll();
		initializeLayout();
	}
	
	private void initializeAll() {
		setLanguage(DEFAULT_LANGUAGE);
		
		myExternalCanvas = new ScrollingDrawingWindow();
		myTurtleController = new TurtleController(
				new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE)),
				INTERNAL_CANVAS_WIDTH/2,
				INTERNAL_CANVAS_HEIGHT/2,
				TURTLE_WIDTH,
				TURTLE_HEIGHT,
				myExternalCanvas.getInternalCanvas());

		//myTurtles = myTurtleController.getActiveTurtles();
		
		interpreter = new CommandTreeInterpreter(myTurtleController);
		myControlPanelRight = new ControlPanelRight(interpreter);		
		myControlTextInput = new ControlTextInput(interpreter, this, myExternalCanvas);
		myControlPanelLeft = new ControlPanelLeft(interpreter, myResources);
	}
	
	private void initializeLayout() {		
		this.setPadding(new Insets(20,20,20,20));
//		this.setTop(new InfoTop());
		this.setCenter(myExternalCanvas);
		this.setBottom(myControlTextInput);
		this.setRight(myControlPanelRight);
		this.setLeft(myControlPanelLeft);
		this.setWidth(Double.MAX_VALUE);
		this.setHeight(Double.MAX_VALUE);
		this.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
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
