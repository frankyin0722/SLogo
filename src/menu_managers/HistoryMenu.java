package menu_managers;

import java.util.ArrayList;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class HistoryMenu extends TitledPane{

	
	TableView<String> historyDisplay;
	ArrayList<String> currentHistory;
	
	public HistoryMenu() {
		historyDisplay = new TableView<String>();
		currentHistory = new ArrayList<String>();
	}
	
	public void clearHistory() {
		currentHistory.clear();
	}
	
	public void addCommand(String command) {
		currentHistory.add(command);
	}
}

