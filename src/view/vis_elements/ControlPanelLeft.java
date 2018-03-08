package view.vis_elements;

import java.util.List;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import view.menus.HistoryMenu;
import view.menus.PenColorMenu;
import view.menus.TurtleMenu;
import view.menus.UserCommandsMenu;

public class ControlPanelLeft extends VBox {

	private List<Turtle> myTurtles;
	private CommandTreeInterpreter interpreter;
	
	public ControlPanelLeft(CommandTreeInterpreter i, List<Turtle> turtles, ResourceBundle resources) {
		myTurtles = turtles;
		interpreter = i;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources),
				new TurtleMenu(resources, myTurtles), 
				new PenColorMenu(resources, myTurtles));
	}
}