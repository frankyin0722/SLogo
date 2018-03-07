package view.vis_elements;

import java.util.ResourceBundle;
import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import view.canvas.DrawingWindow;
import view.menus.CustomVarsMenu;
import view.menus.HelpMenu;
import view.menus.LanguageMenu;
import visual_elements.menu_managers.TurtleSelectionMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	private ResourceBundle myResources;
	private CommandTreeInterpreter interpreter;
	
	public ControlPanelRight(CommandTreeInterpreter i, ResourceBundle resources, DrawingWindow dw) {
		interpreter = i;
		initializeMenus(dw);
	}

	private void initializeMenus(DrawingWindow dw) {
//		myLanguageMenu = new LanguageMenu();
//		this.getChildren().addAll(new VariableMenu(), myLanguageMenu, new CustomVarsMenu());
		this.getChildren().addAll(
//				new VariableMenu(),
				new CustomVarsMenu(interpreter),
				myLanguageMenu = new LanguageMenu(),
				new HelpMenu(),
				new TurtleSelectionMenu(dw));
	}
	
	public ResourceBundle getLanguage() {
		myResources = myLanguageMenu.getLanguage();
		return myResources;
	}
}
