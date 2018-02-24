package menu_managers;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class HistoryMenu extends TitledPane{
	public static final String HISTORY_KEY = "History";
	public static final String ACTVAR_KEY = "ActiveVariables";
	TableView<String> historyDisplay;
	ArrayList<String> currentHistory;
	
	public HistoryMenu(ResourceBundle resources) {
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

