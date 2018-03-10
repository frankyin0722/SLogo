package view.vis_elements;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import view.menus.ColorPaletteMenu;
import view.menus.HistoryMenu;
import view.menus.PenMenu;
import view.menus.TurtleAvatarMenu;
import view.menus.UserCommandsMenu;

public class ControlPanelLeft extends VBox {
	private CommandTreeInterpreter interpreter;
	private ResourceBundle resources;
	private CommandWindow myCommandWindow;
	

	public ControlPanelLeft(CommandTreeInterpreter i, ResourceBundle r, CommandWindow cw) {
		interpreter = i;
		resources = r;
		myCommandWindow = cw;
		initializeMenus();
	}

	private void initializeMenus() {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources, myCommandWindow),
				new TurtleAvatarMenu(resources, interpreter.getTurtleController()), 
				new PenMenu(resources, interpreter.getTurtleController()),
				new ColorPaletteMenu("GreenPalette"));
	}
}