package visual_elements.user_input_presentation;

import javafx.scene.layout.VBox;

public class CommandContainer extends VBox {

	CommandInput textInput;
	HistoryManager historyDisplay;
	
	public CommandContainer() {
		
		textInput = new CommandInput();
		historyDisplay = new HistoryManager();
		this.getChildren().addAll(historyDisplay, textInput);
	
	}
}
