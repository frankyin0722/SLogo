package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import visual_elements.menu_managers.CustomVarsMenu;
import visual_elements.menu_managers.LanguageMenu;
import visual_elements.menu_managers.VariableMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	public ControlPanelRight() {
		initializeMenus();
	}

	private void initializeMenus() {
		myLanguageMenu = new LanguageMenu();
		this.getChildren().addAll(new VariableMenu(), new LanguageMenu(), new CustomVarsMenu());
	}
	
	public ResourceBundle getLanguage() {
		return myLanguageMenu.getLanguage();
	}
}
