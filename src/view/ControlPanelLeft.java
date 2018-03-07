package view;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import menus.HistoryMenu;
import menus.PenColorMenu;
import menus.TurtleMenu;
import menus.UserCommandsMenu;
import turtle.Turtle;

public class ControlPanelLeft extends VBox {

	private Turtle myTurtle;
	private CommandTreeInterpreter interpreter;
	
	public ControlPanelLeft(CommandTreeInterpreter i, Turtle turtle, ResourceBundle resources) {
		myTurtle = turtle;
		interpreter = i;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources),
				new TurtleMenu(resources, myTurtle), 
				new PenColorMenu(resources, myTurtle.getPen()));
	}
}