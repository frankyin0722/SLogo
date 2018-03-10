package view.vis_elements;

import alerts.Resources;
import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import slogo_team08.IConstants;
import view.menus.ColorPaletteMenu;
import view.menus.CustomVarsMenu;
import view.menus.HelpMenu;
import view.menus.TurtleInfoMenu;
/**
 * Class displays menus on right side of Visualization
 * (1) Custom variables
 * (2) Turtle/pen information 
 * (3) Preferences menu
 * (4) Default color options
 * @author xlany, elizabethshulman
 *
 */
public class ControlPanelRight extends VBox implements IConstants {
	
	private CommandTreeInterpreter interpreter;
	private Visualization vis;
	/**
	 * Constructor takes
	 * @param i, interpreter
	 * @param v, visualization
	 * Initializes menus with parameters
	 */
	public ControlPanelRight(CommandTreeInterpreter i, Visualization v) {
		interpreter = i;
		vis = v;
		initializeMenus();
	}
	/**
	 * Method to setup all menus
	 */
	private void initializeMenus() {
		this.getChildren().addAll(
				new CustomVarsMenu(interpreter),
				new TurtleInfoMenu(interpreter.getTurtleController()),
				new HelpMenu(interpreter, vis),
				new ColorPaletteMenu(interpreter,Resources.getString(DEFAULT_COLOR_PALETTE_KEY)));
	}
}
