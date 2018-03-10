package view.vis_elements;

import java.util.ResourceBundle;

import buttons.BaseButton;
import buttons.LoadFileButton;
import buttons.SaveFileButton;
import interpreter.CommandTreeInterpreter;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import parser.Parser;
import view.canvas.ScrollingDrawingWindow;
/**
 * Display that handles transferring commands from front end, 
 * such as user input or loading from file, to back end.
 * Also handles canvas reset, and saving current
 * status to file
 * @author xlany, elizabethshulman
 *
 */
public class ControlTextInput extends HBox {

	private CommandWindow myCommandWindow;
	private BaseButton myRunButton;
	private BaseButton myClearButton;
	private BaseButton myResetButton;
	private LoadFileButton myLoadFileButton;
	private SaveFileButton mySaveFileButton;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	private Visualization myVisualization;
	private ScrollingDrawingWindow myExternalCanvas;
	/**
	 * Constructor takes in 
	 * @param i, interpreter to record history
	 * @param visualization
	 * @param sdw, scrolling drawing window, to modify command 
	 * text input in text area
	 */
	public ControlTextInput(CommandTreeInterpreter i, Visualization visualization, ScrollingDrawingWindow sdw) {
		myVisualization = visualization;
		getLanguage();
		interpreter = i;
		myExternalCanvas = sdw;
		myCommandWindow = new CommandWindow();
		this.getChildren().addAll(myCommandWindow, buttonBox());
		setButtonAction();
		this.setAlignment(Pos.BOTTOM_CENTER);
		}
	/**
	 * Method to initialize buttons:
	 * 	Run, clear, load file, save file, reset canvas
	 * @return display box with buttons
	 */
	private VBox buttonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		myRunButton = new BaseButton("Run");
		myClearButton = new BaseButton("Clear");
		myLoadFileButton = new LoadFileButton();
		mySaveFileButton = new SaveFileButton();
		myResetButton = new BaseButton("Reset Turtle");
		buttons.getChildren().addAll(myRunButton, myClearButton, myLoadFileButton, mySaveFileButton, myResetButton);
		return buttons;
	}
    /**
     * Method to initialize button action
     */
    private void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> resetCommandWindow());
    		myLoadFileButton.setOnAction(e -> myLoadFileButton.loadFile(myCommandWindow));
    		mySaveFileButton.setOnAction(e -> mySaveFileButton.save(interpreter));
    		myResetButton.setOnAction(e -> resetTurtle());
    }
    /**
     * Helper method that sends commands in text area to parser
     * Language retrieved from visualization
     * Add commands to history
     */
	private void inputToParser() {
		getLanguage();
		Parser parser = new Parser(interpreter);
		parser.generateCommandTree(myCommandWindow.getText(), myResources);
		interpreter.addToHistory(myCommandWindow.getText());
		myCommandWindow.clearText();
	}
	/**
	 * Method to update language from visualization
	 */
	private void getLanguage() {
		myResources = myVisualization.getLanguage();
	}
	/**
	 * Method to clear text area
	 */
	private void resetCommandWindow() {
		myCommandWindow.clearText();
	}
	/**
	 * Method to reset all turtles/pen/lines
	 * Recenters external canvas in preparation for new turtles
	 */
	private void resetTurtle() {
		interpreter.getTurtleController().resetTurtles();
		myExternalCanvas.setHvalue(0.5);
		myExternalCanvas.setVvalue(0.5);
	}
	/**
	 * Method to
	 * @return text area (CommandWindow)
	 */
	public CommandWindow getCommandWindow() {
		return myCommandWindow;
	}

}