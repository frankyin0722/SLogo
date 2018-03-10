package view.vis_elements;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import view.menus.HistoryMenu;
import view.menus.PenMenu;
import view.menus.TurtleAvatarMenu;
import view.menus.TurtleInfoMenu;
import view.menus.UserCommandsMenu;

public class ControlPanelLeft extends VBox {
	private CommandTreeInterpreter interpreter;
	private ResourceBundle resources;
	

	public ControlPanelLeft(CommandTreeInterpreter i, ResourceBundle r) {
		interpreter = i;
		resources = r;
		initializeMenus();
	}

	private void initializeMenus() {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources),
				new TurtleAvatarMenu(resources, interpreter.getTurtleController()), 
				new PenMenu(resources, interpreter.getTurtleController()));
	}
}