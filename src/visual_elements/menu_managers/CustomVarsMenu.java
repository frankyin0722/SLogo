package visual_elements.menu_managers;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;
import variables.VariableManager;

public class CustomVarsMenu extends TitledPane implements Listener {
	
	public static final String ACTVAR_KEY = "ActiveVariables";
	private  ListView<VariableManager> varsDisplay;
	private CommandTreeInterpreter interpreter;

	
	public CustomVarsMenu(CommandTreeInterpreter i) {
		varsDisplay = new ListView<VariableManager>();
		interpreter = i;
		i.addListener(this);
		
		this.setText("Variables");
		this.setExpanded(false);
		this.setContent(varsDisplay);
	}

	@Override
	public void update() {
		ObservableList<VariableManager> myvars =FXCollections.observableArrayList (
			    interpreter.getVariables());
		varsDisplay.setItems(myvars);		
	}

}
