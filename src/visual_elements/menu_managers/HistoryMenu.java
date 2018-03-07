package visual_elements.menu_managers;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;

public class HistoryMenu extends TitledPane implements Listener {
	public static final String HISTORY_KEY = "History";
	private ListView<String> historyDisplay;
	private CommandTreeInterpreter interpreter;
	
	public HistoryMenu(CommandTreeInterpreter i, ResourceBundle resources) {
		historyDisplay = new ListView<String>();
		interpreter = i;
		interpreter.addListener(this);
		
		this.setText(resources.getString(HISTORY_KEY));
		this.setExpanded(false);
		this.setContent(historyDisplay);
	}

	@Override
	public void update() {
		ObservableList<String> history =FXCollections.observableArrayList (
			    interpreter.getHistory());
		historyDisplay.setItems(history);	
	}
}

