package visual_elements;

import javafx.scene.layout.VBox;
import option_managers.*;

public class ControlPanel extends VBox {

	public ControlPanel() {
		initializeButtons();
		this.setPrefHeight(200);
	}

	private void initializeButtons() {

		this.getChildren().addAll(new PenOption(), new PenOption(), new PenOption());
	
	}
	
	
	
}
