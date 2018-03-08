package view.canvas;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;
import turtle.TurtleController;
import observables.Listener;

public class DrawingWindow extends Pane implements Listener, IVisualConstants {
	public static final double INITIAL_WIDTH = 695;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	
	private Turtle myTurtle;
	private TurtleController myTurtleController;
	private List<Listener> myListeners;

	public DrawingWindow() {
		setupInitialCanvas();
//		setupTurtle();
//		myTurtle.changeX(myTurtle.getX() + 300);
		initializeColorPicker();
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		this.setBackgroundColor(INITIAL_COLOR);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setStyle("-fx-color-label-visible: false ;");
		colorPicker.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				setBackgroundColor(colorPicker.getValue());
			}
		});
		this.getChildren().add(colorPicker);
	}
		
	private void setBackgroundColor(Color color) {
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
			}
		}
	}
}
	
