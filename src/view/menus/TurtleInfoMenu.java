package view.menus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;
import turtle.TurtleController;

public class TurtleInfoMenu extends TitledPane implements Listener {
	private TurtleController myTurtleController;
	private ListView<String> myInfo;
	public TurtleInfoMenu(TurtleController tc) {
		myTurtleController = tc;
		setupMenu();
		setupContent();
		setupListener();
	}
	
	private void setupMenu() {
		this.setText("Turtle Information");
		this.setExpanded(false);
	}
	
	private void setupContent() {
		myInfo = new ListView<>();
		update();
		this.setContent(myInfo);
	}
	
	private void setupListener() {
		myTurtleController.addTurtleListener(this);
	}

	@Override
	public void update() {
		ObservableList<String> infoText = FXCollections.observableArrayList (
				myTurtleController.getAllTurtleInfo());		
		myInfo.setItems(infoText);
	}
}
