package visual_elements;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import option_managers.*;

public class ControlPanel extends VBox {

	public ControlPanel() {
		initializeButtons();
	}

	private void initializeButtons() {

		this.getChildren().addAll(new PenOption(), new Button(), new Button());
	
	}
	
	
	
}
