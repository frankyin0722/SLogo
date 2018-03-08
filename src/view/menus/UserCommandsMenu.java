package view.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import observables.Listener;

public class UserCommandsMenu extends TitledPane implements Listener {
	private HashMap<String, String> activeUDC;
	private List<Listener> activeUDCListener;
	private 	CommandTreeInterpreter interpreter;
//	private ListView<String> commandsDisplay;
	private VBox commandsDisplay;

	public UserCommandsMenu(CommandTreeInterpreter i, ResourceBundle resources) {
		setupMenu();
//		commandsDisplay = new ListView<>();
		commandsDisplay = new VBox();
		activeUDC = new HashMap<>();
		activeUDCListener = new ArrayList<Listener>();
		interpreter = i;
		i.addUDCListener(this);
		this.setContent(commandsDisplay);
//		activeUDC = i.getActiveUDC();
	}
	
	private void setupMenu() {
//		this.setText(resources.getString(HISTORY_KEY));
		this.setText("User Defined Commands");
		this.setExpanded(false);
	}
	
	@Override
	public void update() {
		ObservableMap<String, String> history =FXCollections.observableMap(interpreter.getActiveUDC());
		System.out.print("XXXXX update " + history.size());
		commandsDisplay.getChildren().clear();
		for (String key : history.keySet()) {
			commandsDisplay.getChildren().add(new Text(key + " " + history.get(key)));
		    System.out.println(key + " " + history.get(key));
		}
	}

}
