package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import menu_managers.HistoryMenu;
import menu_managers.TurtleMenu;

public class ControlPanelLeft extends VBox {

	public ControlPanelLeft(ResourceBundle resources) {
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(new HistoryMenu(resources));
	}
}
