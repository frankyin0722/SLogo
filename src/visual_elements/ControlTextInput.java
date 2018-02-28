package visual_elements;

import java.util.ResourceBundle;

import buttons.ClearButton;
import buttons.ResetButton;

import buttons.RunButton;
import interpreter.CommandTreeInterpreter;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import parser.Parser;
import turtle.Turtle;

public class ControlTextInput extends HBox {

	private CommandWindow myCommandWindow;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private ResetButton myResetButton;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	private Visualization myVisualization;
	
	public ControlTextInput(CommandTreeInterpreter i, Visualization visualization) {
		myVisualization = visualization;
		myResources = myVisualization.getLanguage();
		interpreter = i;
		myCommandWindow = new CommandWindow();
		this.getChildren().addAll(
				myCommandWindow,
				buttonBox());
		setButtonAction();
	}

	private VBox buttonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		myRunButton = new RunButton();
		myClearButton = new ClearButton();
		myResetButton = new ResetButton();
		addButton(buttons, myRunButton);
		addButton(buttons, myClearButton);
		addButton(buttons, myResetButton);
		return buttons;
	}

    private void addButton(VBox buttonBox, Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
    
    private void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> resetCommandWindow());
    		myResetButton.setOnAction(e -> resetTurtle());
    }
    
	private void inputToParser() {
		getLanguage();
		Parser parser = new Parser(interpreter);
		System.out.println(myResources);
		interpreter.interpretAllTrees(parser.generateCommandTree(myCommandWindow.getText(), myResources));
		resetCommandWindow();
	}
	
	private void getLanguage() {
		myResources = myVisualization.getLanguage();

	}
		
	private void resetCommandWindow() {
		myCommandWindow.clearText();
	}
	
	private void resetTurtle() {
		interpreter.getCurrentTurtle().resetTurtle();
	}
}