package visual_elements;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class CommandWindow extends HBox {

	public CommandWindow() {
		
		TextArea userInput = new TextArea();
		this.getChildren().add(userInput);
	}
}
