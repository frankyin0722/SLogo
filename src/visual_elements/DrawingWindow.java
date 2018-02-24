package visual_elements;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import command.Command;
import command.Control.RepeatCommand;
import command.Turtle.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;
import menu_managers.TurtleMenu;

public class DrawingWindow extends Pane {
	public static final int INITIAL_WIDTH = 900;
	public static final int INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	
	private Turtle myTurtle;
	private double myHomeX;
	private double myHomeY;

	public DrawingWindow() {
		setupInitialCanvas();
		setupTurtle();
		
//		myTurtle.changeX(myTurtle.getX() + 100);
//		Command testing = new RepeatCommand(4, new ArrayList<Command>(){{
//			add(new ForwardCommand(myTurtle,50));
//			add(new RightCommand(myTurtle,90));}});
//		System.out.println(testing.execute());
//		this.getChildren().addAll(testing());
//		root.getChildren().addAll(this);
		initializeColorPicker();
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setMaxSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setBackgroundColor(INITIAL_COLOR);
	}
	
	private void setupTurtle() {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));
		myTurtle = new Turtle(turtleImage, INITIAL_WIDTH / 2, INITIAL_HEIGHT / 2, TURTLE_WIDTH, TURTLE_HEIGHT);
		this.getChildren().add(myTurtle);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				setBackgroundColor(colorPicker.getValue());
			}
		});
		this.getChildren().add(colorPicker);
	}
	
	private void initializeTurtleMenu() {
		TurtleMenu turtleMenu = new TurtleMenu();
//		for (Button b: turtleMenu.getTurtleButtons()) {
//			if (b.getGraphic() instanceof ImageView) {
//				b.setOnAction(e -> myTurtle.setImage((ImageView)b.getGraphic()));
//			}
//		}
		this.getChildren().add(turtleMenu);
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
	
	
//	private void changeTurtleImage() {
//		
//	}
	
	public Turtle getDefaultTurtle() {
		return myTurtle;
	}
	
}
	
