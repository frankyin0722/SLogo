package visual_elements.menu_managers;

import buttons.HelpButton;
import javafx.scene.control.TitledPane;

public class HelpMenu extends TitledPane {
		
	public HelpMenu() {
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		this.setText("Help");
		this.setExpanded(false);
		this.setContent(new HelpButton());
	}
}