package visual_elements.user_input_presentation;

import javafx.scene.layout.VBox;

public class CommandContainer extends VBox {

	TextFieldInput textInput;
	HistoryManager historyDisplay;
	
	public CommandContainer() {
		
		textInput = new TextFieldInput();
		historyDisplay = new HistoryManager();
		this.getChildren().addAll(historyDisplay, textInput);
	
	}
}
