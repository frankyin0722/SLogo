package visual_elements;

import buttons.ClearButton;
import buttons.RunButton;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlTextInput extends HBox {

	public ControlTextInput() {
		this.getChildren().addAll(
				new CommandWindow(),
				buttonBox());
	}

	private VBox buttonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		addButton(buttons, new RunButton());
		addButton(buttons, new ClearButton());
		return buttons;
	}

    public void addButton(VBox buttonBox, Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
}