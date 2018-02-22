package visual_elements.user_input_presentation;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;

public class HistoryManager extends ScrollPane {

	ArrayList<String> currentHistory;
	
	public HistoryManager() {
		
		currentHistory = new ArrayList<String>();
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVvalue(this.getVmax());
	}
	
	public void clearHistory() {
		currentHistory.clear();
	}
	
	public void addCommand(String command) {
		currentHistory.add(command);
	}
}

