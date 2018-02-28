package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import visual_elements.menu_managers.CustomVarsMenu;
import visual_elements.menu_managers.HelpMenu;
import visual_elements.menu_managers.LanguageMenu;
import visual_elements.menu_managers.VariableMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	private ResourceBundle myResources;
	public ControlPanelRight() {
		initializeMenus();
	}

	private void initializeMenus() {
		myLanguageMenu = new LanguageMenu();
//		this.getChildren().addAll(new VariableMenu(), myLanguageMenu, new CustomVarsMenu());
		this.getChildren().addAll(new VariableMenu(), myLanguageMenu, new HelpMenu());

	}
	
	public ResourceBundle getLanguage() {
		myResources = myLanguageMenu.getLanguage();
		return myResources;
	}
}
