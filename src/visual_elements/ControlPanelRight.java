package visual_elements;

import javafx.scene.layout.VBox;
import menu_managers.HistoryMenu;

public class ControlPanelRight extends VBox {

	public ControlPanelRight() {
		initializeMenus();
	}

	private void initializeMenus() {
		this.getChildren().addAll(new HistoryMenu());
	}
}
