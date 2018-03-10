package view.vis_elements;

import java.util.ResourceBundle;
import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import view.menus.HistoryMenu;
import view.menus.PenMenu;
import view.menus.TurtleAvatarMenu;
import view.menus.UserCommandsMenu;
/**
 * Class displays menus on left side of Visualization
 * (1) History
 * (2) User defined commands
 * (3) Turtle avatar changer
 * (4) Pen menu
 * @author xlany, elizabethshulman
 *
 */
public class ControlPanelLeft extends VBox {
	private CommandTreeInterpreter interpreter;
	private ResourceBundle resources;
	private CommandWindow myCommandWindow;
	/**
	 * Constructor takes
	 * @param i, interpreter
	 * @param r, language
	 * @param cw, for text area input
	 * Initializes menus with parameters
	 */
	public ControlPanelLeft(CommandTreeInterpreter i, ResourceBundle r, CommandWindow cw) {
		interpreter = i;
		resources = r;
		myCommandWindow = cw;
		initializeMenus();
	}
	/**
	 * Method to setup all menus
	 */
	private void initializeMenus() {
		this.getChildren().addAll(
				new HistoryMenu(interpreter, resources), 
				new UserCommandsMenu(interpreter, resources, myCommandWindow),
				new TurtleAvatarMenu(resources, interpreter.getTurtleController()), 
				new PenMenu(resources, interpreter.getTurtleController()));
	}
}