package visual_elements;

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
	private Turtle myTurtle;
	private CommandWindow myCommandWindow;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private ResetButton myResetButton;
	private CommandTreeInterpreter interpreter;
	
	public ControlTextInput(Turtle turtle) {
		myTurtle = turtle;
		interpreter = new CommandTreeInterpreter(myTurtle);
		myCommandWindow = new CommandWindow();
		this.getChildren().addAll(
				myCommandWindow,
				buttonBox());
		setButtonAction();
		interpreter = new CommandTreeInterpreter(myTurtle);
	}

	private VBox buttonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		myRunButton = new RunButton();
		myClearButton = new ClearButton();
		myResetButton = new ResetButton();
		addButton(buttons, myRunButton);
		addButton(buttons, myClearButton);
		return buttons;
	}

    private void addButton(VBox buttonBox, Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
    
    private void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> myCommandWindow.clearText());
    		myResetButton.setOnAction(e -> resetTurtle());
    }
    
	private void inputToParser() {
		System.out.print("we are here");
		Parser parser = new Parser();
		interpreter.interpretAllTrees(parser.generateCommandTree(myCommandWindow.getText(), "resources.languages/English"));
	}
	
	private void resetTurtle() {
		myTurtle.resetTurtle();
	}
}