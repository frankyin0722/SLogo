package view.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;
import turtle.Turtle;
import turtle.TurtleController;
import view.supplements.HBoxPenChanger;

public class PenMenu extends TitledPane implements Listener {

	private TurtleController myTurtles;
	private ArrayList<HBoxPenChanger> penModifiers;
	private List<Turtle> allTurtles;
	private ListView<HBoxPenChanger> penDisplay;
	
	public PenMenu(ResourceBundle resources, TurtleController tc) {
		myTurtles = tc;
		initializeTitledPane();
		setupPenChangers();
		myTurtles.addTurtleListener(this);

	}
	
	private void initializeTitledPane() {
		this.setMaxWidth(Double.MAX_VALUE);
		this.setText("Change Pen Attributes");
		this.setExpanded(false);
		penModifiers = new ArrayList<HBoxPenChanger>();
		penDisplay = new ListView<HBoxPenChanger>(
				FXCollections.observableArrayList(penModifiers));
		this.setContent(penDisplay);
	}
	
	private void setupPenChangers() {
		allTurtles = myTurtles.getAllTurtles();
		penModifiers.clear();
		penModifiers.add(new HBoxPenChanger());
		allTurtles.forEach(turtle -> penModifiers.add(
				new HBoxPenChanger(turtle, myTurtles.getAllTurtles().indexOf(turtle))));
		penDisplay.setItems(FXCollections.observableArrayList(penModifiers));
	}
	
	@Override
	public void update() {
		setupPenChangers();
	}
	
}
