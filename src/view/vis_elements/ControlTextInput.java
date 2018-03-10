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
	
	public ControlTextInput(CommandTreeInterpreter i, Visualization visualization, ScrollingDrawingWindow sdw) {
		myVisualization = visualization;
		getLanguage();
		interpreter = i;
		myExternalCanvas = sdw;
		myCommandWindow = new CommandWindow();
		this.getChildren().addAll(myCommandWindow,buttonBox());
		setButtonAction();
		this.setAlignment(Pos.BOTTOM_CENTER);
		}

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
    
    private void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> resetCommandWindow());
    		myLoadFileButton.setOnAction(e -> myLoadFileButton.loadFile(myCommandWindow));
    		mySaveFileButton.setOnAction(e -> mySaveFileButton.save(interpreter));
    		myResetButton.setOnAction(e -> resetTurtle());
    }
    
	private void inputToParser() {
		getLanguage();
		Parser parser = new Parser(interpreter);
		parser.generateCommandTree(myCommandWindow.getText(), myResources);
		interpreter.addToHistory(myCommandWindow.getText());
		resetCommandWindow();
	}
	
	private void getLanguage() {
		myResources = myVisualization.getLanguage();
	}
		
	private void resetCommandWindow() {
		myCommandWindow.clearText();
	}
	
	private void resetTurtle() {
		interpreter.getTurtleController().resetTurtles();
		myExternalCanvas.setHvalue(0.5);
		myExternalCanvas.setVvalue(0.5);
	}

}