package visual_elements.menu_managers;

import java.util.ArrayList;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;

public class HistoryMenu extends TitledPane implements Listener {
	public static final String HISTORY_KEY = "History";
	public static final String ACTVAR_KEY = "ActiveVariables";
	ListView<String> historyDisplay;
	ArrayList<String> currentHistory;
	CommandTreeInterpreter interpreter;
	
	public HistoryMenu(CommandTreeInterpreter i, ResourceBundle resources) {
		historyDisplay = new ListView<String>();
		interpreter = i;
		i.addListener(this);
		this.setText(resources.getString(HISTORY_KEY));
		this.setExpanded(false);
		this.setContent(historyDisplay);
		currentHistory = interpreter.getHistory();
	}
	
	public void clearHistory() {
		currentHistory.clear();
	}
	
	public void addCommand(String command) {
		currentHistory.add(command);
	}

	@Override
	public void update() {
		ObservableList<String> history =FXCollections.observableArrayList (
			    interpreter.getHistory());
		historyDisplay.setItems(history);	
	}
}

