package view.vis_elements;

import java.util.List;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import turtle.TurtleController;
import view.menus.ColorPaletteMenu;
import view.menus.HistoryMenu;
import view.menus.PenColorMenu;
import view.menus.TurtleMenu;
import view.menus.UserCommandsMenu;

public class ControlPanelLeft extends VBox {
	private TurtleController myTurtleController;
	private CommandTreeInterpreter interpreter;
	
	public ControlPanelLeft(CommandTreeInterpreter i, TurtleController tc, ResourceBundle resources) {
		myTurtleController = tc;
		interpreter = i;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources),
				new TurtleMenu(resources, myTurtleController), 
				new PenColorMenu(resources, myTurtleController),
				new ColorPaletteMenu("BluePalette"));
	}
}