package view.menus;

import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import observables.Listener;
import parser.Parser;

/**
 * yikes languages can't be changed here
 * @author elizabethshulman
 *
 */
public class HistoryMenu extends TitledPane implements Listener {
	
	private ListView<String> historyDisplay;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	
	public HistoryMenu(CommandTreeInterpreter i, ResourceBundle resources) {
		historyDisplay = new ListView<String>();
		interpreter = i;
		interpreter.addListener(this);
		myResources = resources;
		initializeMenu();
	}

	private void initializeMenu() {
		callHistoryOnClick();
		this.setText("History");
		this.setExpanded(false);
		this.setContent(historyDisplay);
	}
	
	@Override
	public void update() {
		ObservableList<String> history =FXCollections.observableArrayList (
			    interpreter.getHistory());
		historyDisplay.setItems(history);	
	}
	
	private void callHistoryOnClick() {
		historyDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
			Parser parser = new Parser(interpreter);
			String repeatCommand = historyDisplay.getSelectionModel().getSelectedItem();
			parser.generateCommandTree(repeatCommand, myResources);
			interpreter.addToHistory(repeatCommand);
			}
		});
	}
}

