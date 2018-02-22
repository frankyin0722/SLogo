package visual_elements;

import javafx.scene.layout.VBox;
import menu_managers.HistoryMenu;

public class ControlPanelLeft extends VBox {

	public ControlPanelLeft() {
		initializeMenus();
	}

	private void initializeMenus() {
		this.getChildren().addAll(new HistoryMenu());
	}
}
