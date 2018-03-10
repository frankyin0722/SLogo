package view.canvas;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import observables.Listener;
import slogo_team08.IConstants;
import turtle.Turtle;
import turtle.TurtleController;

public class DrawingWindow extends Pane implements Listener, IConstants {
	private TurtleController myTurtleController;

	public DrawingWindow() {
		setupInitialCanvas();

	}
	

	private void setupInitialCanvas() {
		this.setPrefSize(INTERNAL_CANVAS_WIDTH, INTERNAL_CANVAS_HEIGHT);
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		this.setBackgroundColor(INITIAL_COLOR);
	}
	
	public void setBackgroundColor(Color color) {
		String hex = String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
		this.setStyle(
				"-fx-background-color: " + hex + ";"
				);
	}
	
	public void addTurtle(Turtle turtle) {
		this.getChildren().add(turtle.getImageView());
	}
	
	public void addTurtles(List<Turtle> turtles) {
		this.getChildren().addAll(turtles);
	}
	
	public void setupListener(TurtleController tc) {
		myTurtleController = tc;
		myTurtleController.addTurtleListener(this);
	}

	@Override
	public void update() {
		ObservableList<Turtle> turtles =FXCollections.observableArrayList (
				myTurtleController.getActiveTurtles());
		updateTurtles(turtles);
	}
	
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
	
