package view.canvas;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import observables.Listener;
import turtle.Turtle;
import turtle.TurtleController;
import observables.Listener;
import slogo_team08.IConstants;

public class DrawingWindow extends Pane implements Listener, IConstants {
	public static final double INITIAL_WIDTH = 695;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
//	public static final String IMAGE_PATH = "./images/";
//	public static final String FRANKLIN_IMAGE = "franklin.jpg";
//	public static final String TURTLE_IMAGE = "cute_turtle.png";
	
	private Turtle myTurtle;
	private TurtleController myTurtleController;
	private List<Turtle> myPreviousTurtles;
//	private List<Listener> myListeners;

	public DrawingWindow() {
		setupDrawingWindow();
		setupInitialCanvas();
//		setupTurtle();
//		myTurtle.changeX(myTurtle.getX() + 300);
	}
	
	private void setupDrawingWindow() {
		myPreviousTurtles = new ArrayList<>();
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
//		this.getChildren().add(turtle);
	}
	
	public void addTurtles(List<Turtle> turtles) {
		this.getChildren().addAll(turtles);
	}
	
	public Turtle getDefaultTurtle() {
		return myTurtle;
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
				t.setOnMouseClicked(e -> changeTurtleStatus(t, e));
			}
		}
	}
	
	private void changeTurtleStatus(Turtle t, Event e) {
		myTurtleController.changeTurtleStatus(t);
	}
	

//	private void updateTurtles(ObservableList<Turtle> turtles) {
//		for (Turtle t: turtles) {
//			if (!turtleInList(myPreviousTurtles, t)) {
//				this.getChildren().add(t);
//			}
//		}
//		for (Turtle t: myPreviousTurtles) {
//			if (!turtleInList(turtles, t)) {
//			}
//		}
//	}
//	
//	private boolean turtleInList(List<Turtle> list, Turtle obj) {
//		for (Turtle o: list) {
//			if (o.equals(obj)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	private void updatePreviousTurtles(ObservableList<Turtle> turtles) {
//		myPreviousTurtles.clear();
//		for (Turtle t: turtles) {
//			myPreviousTurtles.add(t);
//		}
//	}
}
	
