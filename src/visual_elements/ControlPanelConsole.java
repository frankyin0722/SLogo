package visual_elements;

import buttons.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class ControlPanelConsole extends HBox {

	TextArea textbox;
	public ControlPanelConsole() {
		initializeTextArea();
		initializeButtons();
	}

	private void initializeTextArea() {
		textbox = new TextArea();
		textbox.setWrapText(true);
		this.getChildren().add(textbox);
	}

	private void initializeButtons() {
		RunButton run = new RunButton(textbox);
		this.getChildren().add(run);
	}
}
