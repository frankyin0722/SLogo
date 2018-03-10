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
 * Initializes and arranges each of the elements within the scene
 * Highest level of front end, and has instances of most major classes
 * Left and right: ControlPanelLeft, ControlPanelRight
 * Button: ControlTextInput
 * Center: ScrollingDrawingWindow
 * 
 * @author elizabethshulman
 * @author xlany
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
	/**
	 * Constructor initializes front-end parts and
	 * arranges in favorable locations
	 */
	public Visualization() {
		initializeAll();
		initializeLayout();
	}
	/**
	 * Method to initialize all instance variables of
	 * relevent classes
	 */
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
		interpreter = new CommandTreeInterpreter(myTurtleController);
		myControlPanelRight = new ControlPanelRight(interpreter, this);		
		myControlTextInput = new ControlTextInput(interpreter, this, myExternalCanvas);
		myControlPanelLeft = new ControlPanelLeft(interpreter, myResources, myControlTextInput.getCommandWindow());
	}
	/**
	 * Method sets layout of major views
	 */
	private void initializeLayout() {		
		this.setPadding(new Insets(20,20,20,20));
		this.setCenter(myExternalCanvas);
		this.setBottom(myControlTextInput);
		this.setRight(myControlPanelRight);
		this.setLeft(myControlPanelLeft);
		this.setWidth(Double.MAX_VALUE);
		this.setHeight(Double.MAX_VALUE);
		this.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
	}
	/**
	 * Method sets default language by
	 * @param language
	 */
	public void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PACKAGE + language);
	}
	/**
	 * Method to 
	 * @return current language
	 */
	public ResourceBundle getLanguage() {
		return myResources;
	}

}
