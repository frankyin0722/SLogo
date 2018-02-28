package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import menu_managers.HelpMenu;
import menu_managers.LanguageMenu;
import menu_managers.VariableMenu;

public class ControlPanelRight extends VBox {
	private LanguageMenu myLanguageMenu;
	private ResourceBundle myResources;
	public ControlPanelRight() {
		initializeMenus();
	}

	private void initializeMenus() {
		myLanguageMenu = new LanguageMenu();
		this.getChildren().addAll(new VariableMenu(), myLanguageMenu, new HelpMenu());
	}
	
	public ResourceBundle getLanguage() {
		myResources = myLanguageMenu.getLanguage();

//		System.out.print(myResources.getString("Backward"));
		return myResources;
	}
}
