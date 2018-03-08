package view.menus;

import java.util.ArrayList;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import observables.Listener;
import variables.VariableManager;

public class CustomVarsMenu extends TitledPane implements Listener {
	
	public static final String ACTVAR_KEY = "ActiveVariables";
	private HBox varsDisplay;
	private VBox keyCol;
	private VBox valCol;
	private ListView<String> keyView;
	private ListView<Object> valView;
	private CommandTreeInterpreter interpreter;
	VariableManager myvars;

	
	public CustomVarsMenu(CommandTreeInterpreter i) {
		interpreter = i;
		interpreter.addListener(this);
		initializeTable();

		
		this.setText("Variables");
		this.setExpanded(false);
	}

	private void initializeTable() {
		varsDisplay = new HBox();
		varsDisplay.setPrefWidth(USE_PREF_SIZE);
		keyCol = new VBox();
		valCol = new VBox();	
		keyView = new ListView<String>();
		valView = new ListView<Object>();
		keyCol.getChildren().add(keyView);
		valCol.getChildren().add(valView);
		varsDisplay.getChildren().addAll(keyCol, valCol);
		this.setContent(varsDisplay);
	}

	private void setupTable(VariableManager myvars) {	
		ObservableList<String> tempkeys = FXCollections.observableArrayList(new ArrayList<String>(myvars.getNames()));
		ArrayList<Object> tempvals = new ArrayList<Object>();
		for(String s : tempkeys) {
			tempvals.add(myvars.getVariable(s).getValue()); 
		}
		
		keyCol.getChildren().remove(keyView);
		valCol.getChildren().remove(valView);
		
		keyView = new ListView<String>(tempkeys);
		valView = new ListView<Object>(FXCollections.observableArrayList(tempvals));
		
		keyCol.getChildren().add(keyView);
		valCol.getChildren().add(valView);
		setupKeyEvent();
		setupValEvent();

	}

	private void setupKeyEvent() {
		keyView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
				updateKey(keyView.getSelectionModel().getSelectedItem());
			}
		});
	}
	
	private void updateKey(String key) {
		System.out.println(myvars.getVariable(key).getValue());
		SceneChangeVariable keystage = new SceneChangeVariable("key", key, this);
		update();
	}
	
	private void setupValEvent() {
		valView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
				updateVal(valView.getItems().indexOf(valView.getSelectionModel().getSelectedItem()));
			}
		});
	}
	
	private void updateVal(int index) {
		keyView.getSelectionModel().select(index);
		SceneChangeVariable varstage = new SceneChangeVariable("val",
				keyView.getSelectionModel().getSelectedItem(),
				this);
		update();
	}
	
	@Override
	public void update() {
		myvars = interpreter.getVariables();
		setupTable(myvars);
	}
	
	public VariableManager getVariableManager() {
		return myvars;
	}

}
