package view.vis_elements;

import java.util.List;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import turtle.TurtleController;
import view.menus.ColorPaletteMenu;
import view.menus.HistoryMenu;
import view.menus.PenMenu;
import view.menus.TurtleMenu;
import view.menus.UserCommandsMenu;

public class ControlPanelLeft extends VBox {

	private List<Turtle> myTurtles;
	private CommandTreeInterpreter interpreter;
	private TurtleController myController;
	
	public ControlPanelLeft(CommandTreeInterpreter i, List<Turtle> turtles, TurtleController tc, ResourceBundle resources) {
		myTurtles = turtles;
		interpreter = i;
		myController = tc;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources),
				new TurtleMenu(resources, myTurtles), 
				new PenMenu(resources, myController),
				new ColorPaletteMenu("GreenPalette"));
	}
}