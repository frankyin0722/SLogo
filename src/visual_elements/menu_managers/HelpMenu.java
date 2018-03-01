package visual_elements.menu_managers;

import buttons.HelpButton;
import javafx.scene.control.TitledPane;

public class HelpMenu extends TitledPane {
	
	public static final String COMMANDS_WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
	
	public HelpMenu() {
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		this.setText("Help");
		this.setExpanded(false);
		this.setContent(new HelpButton());
	}
}