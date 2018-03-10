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
 * Menu displays past commands, and clicking on command runs it
 * Note: currently cannot run non-English commands from history
 * @author elizabethshulman, xlany
 *
 */
public class HistoryMenu extends TitledPane implements Listener {

	private ListView<String> historyDisplay;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	/**
	 * Constructor that takes in
	 * @param i, interpreter to run new commands
	 * @param resources, for language
	 */
	public HistoryMenu(CommandTreeInterpreter i, ResourceBundle resources) {
		historyDisplay = new ListView<String>();
		interpreter = i;
		interpreter.addListener(this);
		myResources = resources;
		initializeMenu();
		callHistoryOnClick();
	}
	/**
	 * Method to initialize menu
	 */
	private void initializeMenu() {
		this.setText("History");
		this.setExpanded(false);
		this.setContent(historyDisplay);
	}
	/**
	 * Method to handle mouse click
	 * If user clicks on command, it is executed and added to history
	 */
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
	/**
	 * Listener method: if interpreter receives new commands, 
	 * display them in history
	 */
	@Override
	public void update() {
		ObservableList<String> history =FXCollections.observableArrayList (
			    interpreter.getHistory());
		historyDisplay.setItems(history);	
	}
}

