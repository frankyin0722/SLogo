package view.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import observables.Listener;
import slogo_team08.IConstants;
import turtle.Turtle;
import turtle.TurtleController;
/**
 * Pane that displays turtle and lines
 * Turtles and lines are added to group
 * @author xlany, elizabethshulman
 *
 */
public class DrawingWindow extends Pane implements Listener, IConstants {
	private TurtleController myTurtleController;
	/**
	 * Constructor sets up canvas
	 */
	public DrawingWindow() {
		setupInitialCanvas();
	}
	/**
	 * Initializes canvas size and background color
	 */
	private void setupInitialCanvas() {
		this.setPrefSize(INTERNAL_CANVAS_WIDTH, INTERNAL_CANVAS_HEIGHT);
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		this.setBackgroundColor(INITIAL_COLOR);
	}
	/**
	 * Method to change background color by
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		String hex = String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
		this.setStyle(
				"-fx-background-color: " + hex + ";"
				);
	}
	/**
	 * Methods to make DrawingWindow a listener for
	 * @param TurtleController tc 
	 * to easily update new turtles
	 */
	public void setupListener(TurtleController tc) {
		myTurtleController = tc;
		myTurtleController.addTurtleListener(this);
	}
	/**
	 * Listener method: updates on new turtles created in TurtleController
	 */
	@Override
	public void update() {
		ObservableList<Turtle> turtles =FXCollections.observableArrayList (
				myTurtleController.getActiveTurtles());
		updateTurtles(turtles);
	}
	/**
	 * Helper method that adds new turtle from
	 * @param turtles
	 * to DrawingWindow for visualization
	 */
	private void updateTurtles(ObservableList<Turtle> turtles) {
		for (Turtle t: turtles) {
			if (!this.getChildren().contains(t)) {
				this.getChildren().add(t);
				t.setOnMouseClicked(e -> {
					if (e.getClickCount() == 1) {
						myTurtleController.changeTurtleStatus(t);
					}
				});
			}
		}
	}
}
	
