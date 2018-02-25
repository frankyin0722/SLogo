package visual_elements;

import buttons.ClearButton;
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
	
	public ControlTextInput(Turtle turtle) {
		myTurtle = turtle;
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
		addButton(buttons, myRunButton);
		addButton(buttons, myClearButton);
		return buttons;
	}

    public void addButton(VBox buttonBox, Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
    
    public void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> myCommandWindow.clearText());
    }
    
	private void inputToParser() {
		System.out.print("we are here");
		Parser parser = new Parser();
		CommandTreeInterpreter interpreter = new CommandTreeInterpreter(myTurtle);
		interpreter.interpretAllTrees(parser.generateCommandTree(myCommandWindow.getText(), "resources.languages/English"));
	}
}