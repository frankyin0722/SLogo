package visual_elements;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import visual_elements.menu_managers.CustomVarsMenu;
import visual_elements.menu_managers.HelpMenu;
import visual_elements.menu_managers.LanguageMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	private ResourceBundle myResources;
	private CommandTreeInterpreter interpreter;
	
	public ControlPanelRight(CommandTreeInterpreter i, ResourceBundle resources) {
		interpreter = i;

		initializeMenus();
	}

	private void initializeMenus() {
		myLanguageMenu = new LanguageMenu();
//		this.getChildren().addAll(new VariableMenu(), myLanguageMenu, new CustomVarsMenu());
		this.getChildren().addAll(
//				new VariableMenu(),
				new CustomVarsMenu(interpreter),
				myLanguageMenu, 
				new HelpMenu());

	}
	
	public ResourceBundle getLanguage() {
		myResources = myLanguageMenu.getLanguage();
		return myResources;
	}
}
