package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import menu_managers.LanguageMenu;
import menu_managers.VariableMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	public ControlPanelRight() {
		initializeMenus();
	}

	private void initializeMenus() {
		myLanguageMenu = new LanguageMenu();
		this.getChildren().addAll(new VariableMenu(), new LanguageMenu());
	}
	
	public ResourceBundle getLanguage() {
		return myLanguageMenu.getLanguage();
	}
}
