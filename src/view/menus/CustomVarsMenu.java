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
import view.supplements.StageVariableChanger;
/**
 * Menu displays current custom variables and
 * allows user to change variable name 
 * @author elizabethshulman
 *
 */
public class CustomVarsMenu extends TitledPane implements Listener {
	
	private HBox varsDisplay;
	private VBox keyCol;
	private VBox valCol;
	private ListView<String> keyView;
	private ListView<Object> valView;
	private CommandTreeInterpreter interpreter;
	private VariableManager myvars;
	/**
	 * Constructor takes in
	 * @param i, interpreter
	 * Sets up display for active variables
	 */
	public CustomVarsMenu(CommandTreeInterpreter i) {
		interpreter = i;
		interpreter.addListener(this);
		initializeTableElements();		
		this.setText("Variables");
		this.setExpanded(false);
	}
	/**
	 * Method initializes dual VBoxs to display
	 * (1) variable name aka key
	 * (2) variable value aka val
	 */
	private void initializeTableElements() {
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
	/**
	 * Method adds variables to display boxes and
	 * sets up key and value changes on click
	 * @param myvars
	 */
	private void buildTableContents(VariableManager myvars) {	
		ObservableList<String> tempkeys = FXCollections.observableArrayList(new ArrayList<String>(myvars.getNames()));
		ArrayList<Object> tempvals = new ArrayList<>();
		for(String s : tempkeys) {
			tempvals.add(myvars.getVariable(s).getValue()); 
		}
		resetLists(tempkeys, tempvals);
		setupKeyEvent();
		setupValEvent();

	}

	private void resetLists(ObservableList<String> keylist, ArrayList<Object> vallist) {
		keyCol.getChildren().remove(keyView);
		valCol.getChildren().remove(valView);
		keyView = new ListView<String>(keylist);
		valView = new ListView<Object>(FXCollections.observableArrayList(vallist));
		keyCol.getChildren().add(keyView);
		valCol.getChildren().add(valView);
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
		StageVariableChanger keystage = new StageVariableChanger("key", key, this);
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
		StageVariableChanger varstage = new StageVariableChanger("value",
				keyView.getSelectionModel().getSelectedItem(), this);
		update();
	}
	
	@Override
	public void update() {
		myvars = interpreter.getVariables();
		buildTableContents(myvars);
	}
	
	public VariableManager getVariableManager() {
		return myvars;
	}
}