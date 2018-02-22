package visual_elements.user_input_presentation;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;

public class HistoryManager extends ScrollPane {

	ArrayList<String> currentHistory;
	
	public HistoryManager() {
		
		currentHistory = new ArrayList<String>();

		addCommand("string");
	}
	
	public void initializeThisPane() {
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVvalue(this.getVmax());
		this.setPrefViewportHeight(10);
		this.setPrefViewportWidth(10);
	}
	
	public void clearHistory() {
		currentHistory.clear();
		this.setContent(new ListToTextHelper(currentHistory));
	}
	
	public void addCommand(String command) {
		currentHistory.add(command);
		ArrayList<String> tester = new ArrayList<>();
		tester.add("teststring");
		tester.add("test");
		tester.add("teststring");
		tester.add("test");
//		tester.add("teststring");
//		tester.add("test");
		this.setContent(new ListToTextHelper(tester));
	}
}

