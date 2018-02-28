package visual_elements;

import java.util.ResourceBundle;

import buttons.ClearButton;
import buttons.ResetButton;

import buttons.RunButton;
import interpreter.CommandTreeInterpreter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import menu_managers.LanguageMenu;
import parser.Parser;
import turtle.Turtle;

public class ControlTextInput extends HBox {
	private Turtle myTurtle;
	private CommandWindow myCommandWindow;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private ResetButton myResetButton;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	private Visualization myVisualization;
	
	public ControlTextInput(Turtle turtle, Visualization visualization) {
		myTurtle = turtle;
		myVisualization = visualization;
		myResources = visualization.getLanguage();
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
		interpreter.interpretAllTrees(parser.generateCommandTree(myCommandWindow.getText(), "resources.languages/English"));
		resetCommandWindow();
	}
	
	private void getLanguage() {
		myResources = myVisualization.getLanguage();

	}
		
	private void resetCommandWindow() {
		myCommandWindow.clearText();
	}
	
	private void resetTurtle() {
		myTurtle.resetTurtle();
	}
}