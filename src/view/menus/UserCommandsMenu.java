package view.menus;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import observables.Listener;
import parser.CommandNode;
import view.vis_elements.CommandWindow;
/**
 * Menu that stores user created commands
 * Clicking on command will send the command to CommandWindow
 * where user can edit parameters and run command
 * @author xlany, elizabethshulman
 *
 */
public class UserCommandsMenu extends TitledPane implements Listener {
	private 	CommandTreeInterpreter interpreter;
	private ListView<String> commandsDisplay;
	private CommandWindow myCommandWindow;
	/**
	 * Constructor that gets
	 * user defined commands from @param i
	 * languages from @param resources
	 * sends commands upon click to @param cw
	 */
	public UserCommandsMenu(CommandTreeInterpreter i, ResourceBundle resources, CommandWindow cw) {
		interpreter = i;
		myCommandWindow = cw;
		setupMenu();
		setupListeners();
		setupMouseEventHandler(myCommandWindow);
	}
	/**
	 * Method to initialize menu
	 */
	private void setupMenu() {
		commandsDisplay = new ListView<>();
		this.setText("User Defined Commands");
		this.setExpanded(false);
		this.setContent(commandsDisplay);
	}
	/**
	 * Method adds this class as listener for updates in 
	 * user defined commands in interpreter
	 */
	private void setupListeners() {
		interpreter.addUDCListener(this);
	}
	/**
	 * Method that sends command name and parameters to
	 * @param cw CommandWindow upon click
	 */
	private void setupMouseEventHandler(CommandWindow cw) {
		commandsDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
				String repeatCommand = commandsDisplay.getSelectionModel().getSelectedItem();
				Map<String, List<CommandNode>> para = interpreter.getUserCommandParameters();
				cw.addText(repeatCommand + " ");
				cw.addText(paraToString(para.get(repeatCommand)));
			}
		});
	}
	/**
	 * Listener method: updates when interpreter has new user commands
	 * Displays the new commands in menu
	 */
	@Override
	public void update() {
		ObservableList<String> names = FXCollections.observableArrayList(interpreter.getUserCommands().keySet());
		commandsDisplay.setItems((ObservableList<String>) names);
	}
	/**
	 * Helper method to turn
	 * @param list of parameters into 
	 * @return string
	 * Formatted for user to run in CommandWindow
	 */
	private String paraToString(List<CommandNode> list) {
		String s = "";
		for (CommandNode c: list) {
			s += (c.getCommandName() + " ");
		}
		return s.substring(0, s.length()) + "\n";
	}
}
