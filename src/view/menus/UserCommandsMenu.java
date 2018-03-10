package view.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import observables.Listener;
import parser.CommandNode;
import view.vis_elements.CommandWindow;

public class UserCommandsMenu extends TitledPane implements Listener {
	private 	CommandTreeInterpreter interpreter;
	private ListView<String> commandsDisplay;
	private CommandWindow myCommandWindow;

	public UserCommandsMenu(CommandTreeInterpreter i, ResourceBundle resources, CommandWindow cw) {
		setupMenu();
		commandsDisplay = new ListView<>();
		interpreter = i;
		myCommandWindow = cw;
		i.addUDCListener(this);
		this.setContent(commandsDisplay);
		setupMouseEventHandler(myCommandWindow);
	}
	
	private void setupMenu() {
//		this.setText(resources.getString(HISTORY_KEY));
		this.setText("User Defined Commands");
		this.setExpanded(false);
	}
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void update() {
		ObservableList<String> names = FXCollections.observableArrayList(interpreter.getUserCommands().keySet());
		commandsDisplay.setItems((ObservableList<String>) names);
	}
	
	private String paraToString(List<CommandNode> list) {
		String s = "";
		for (CommandNode c: list) {
			s += (c.getCommandName() + " ");
		}
		return s.substring(0, s.length()-1) + "\n";
	}

}
