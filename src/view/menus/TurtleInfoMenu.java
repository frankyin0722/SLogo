package view.menus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;
import turtle.TurtleController;
/**
 * Menu that displays turtle and pen information to user
 * @author xlany, elizabethshulman
 *
 */
public class TurtleInfoMenu extends TitledPane implements Listener {
	private TurtleController myTurtleController;
	private ListView<String> myInfo;
	/**
	 * Constructor takes
	 * @param tc, TurtleController
	 * and sets up content from its active turtles
	 */
	public TurtleInfoMenu(TurtleController tc) {
		myTurtleController = tc;
		setupMenu();
		setupContent();
		setupListener();
	}
	/**
	 * Method to initialize menu
	 */
	private void setupMenu() {
		this.setText("Turtle Information");
		this.setExpanded(false);
	}
	/**
	 * Method to initialize content with 
	 * default turtle
	 */
	private void setupContent() {
		myInfo = new ListView<>();
		update();
		this.setContent(myInfo);
	}
	/**
	 * Method sets this class to be listener for changes
	 * in list of turtles (e.g. new turtle created)
	 */
	private void setupListener() {
		myTurtleController.addTurtleListener(this);
	}
	/**
	 * Listener method: when new turtle is created, its information
	 * is added to information display
	 */
	@Override
	public void update() {
		ObservableList<String> infoText = FXCollections.observableArrayList (
				myTurtleController.getAllTurtleInfo());		
		myInfo.setItems(infoText);
	}
}
