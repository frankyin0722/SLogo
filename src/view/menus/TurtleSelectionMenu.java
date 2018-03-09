package view.menus;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import observables.Listener;
import turtle.Turtle;
import turtle.TurtleController;
public class TurtleSelectionMenu extends VBox implements Listener{
	public static final double INITIAL_WIDTH = 700;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
	public static final String DEFAULT_IMAGE = "cute_turtle.png";

	private TurtleController myTurtleController;
	private DoubleProperty myHomeWidth;
	private DoubleProperty myHomeHeight;
	public TurtleSelectionMenu(TurtleController tc) {
		myTurtleController = tc;
		setupMenu();
//		setupListener();
//		setupHomeLocation(dw);
//		addTurtle(dw);
	}
	
	private void setupMenu() {
		this.getChildren().add(new Text("Active Turtles"));
		this.setPadding(new Insets(12, 12, 12, 12));
//		this.setStyle();
	}
	
	
//	private void addTurtle(DrawingWindow dw) {
//		HBox hbox = new HBox();
//		StackPane sp = new StackPane();
//		CheckBox cb = new CheckBox();
//		Turtle turtle = defaultTurtle();
//		sp.getChildren().add(turtle.getImageView());
//		hbox.getChildren().addAll(cb, sp);
//		dw.addTurtle(turtle);
//		this.getChildren().add(hbox);
//	}
//	
//	private void setupListener();

	@Override
	public void update() {
		ObservableList<Turtle> turtles =FXCollections.observableArrayList (
				myTurtleController.getActiveTurtles());
	}
	
	
	
}
