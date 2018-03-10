package view.menus;

import buttons.CanvasColorButton;
import buttons.HelpButton;
import buttons.LanguageChangeButton;
import interpreter.CommandTreeInterpreter;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import view.vis_elements.Visualization;
/**
 * Menu that lead to preference options
 * (1) help, for list of available commands
 * (2) change canvas color
 * (3) change language
 * @author elizabethshulman, xlany
 *
 */
public class HelpMenu extends TitledPane {
	
	private CommandTreeInterpreter interpreter;
	private Visualization vis;
	/**
	 * Constructor takes
	 * @param i, for CanvasColorButton
	 * @param v, to change language in LanguageChangeButton
	 * Sets up all buttons
	 */
	public HelpMenu(CommandTreeInterpreter i, Visualization v) {
		interpreter = i;
		vis = v;
		setupHelpMenu();
	}
	/**
	 * Method that initializes menu with preference buttons
	 */
	private void setupHelpMenu() {
		this.setText("Preferences");
		this.setExpanded(false);
		VBox buttonList = new VBox();
		buttonList.setSpacing(10);
		buttonList.getChildren().addAll(
				new HelpButton(),
				new CanvasColorButton(interpreter),
				new LanguageChangeButton(vis));
		this.setContent(buttonList);
	}	
}