package menu_managers;

import java.util.ArrayList;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import visual_elements.ResourceBundle;

public class HistoryMenu extends TitledPane{
	public static final String HISTORY_KEY = "History";
	TableView<String> historyDisplay;
	ArrayList<String> currentHistory;
	
	public HistoryMenu(java.util.ResourceBundle resources) {
		historyDisplay = new TableView<String>();
		currentHistory = new ArrayList<String>();
		this.setText(resources.getString(HISTORY_KEY));
	}
	
	public void clearHistory() {
		currentHistory.clear();
	}
	
	public void addCommand(String command) {
		currentHistory.add(command);
	}
	
}

