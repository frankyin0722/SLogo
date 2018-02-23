package visual_elements;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import command.Command;
import command.Turtle.BackwardCommand;
import command.Turtle.ForwardCommand;
import command.Turtle.RightCommand;
import command.Control.RepeatCommand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Turtle;

public class DrawingWindow extends Pane {
	public static final int INITIAL_WIDTH = 900;
	public static final int INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;

//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
//	public static final String TEST_IMAGE = "./src/images/cute_turtle.png";
	private Turtle myTurtle;
	private double myHomeX;
	private double myHomeY;

	public DrawingWindow() {
		setupInitialCanvas();
		getNewTurtle();
//		setupTurtle();
		
//		myTurtle.changeX(myTurtle.getX() + 100);
		myTurtle.setDirection(Math.PI);
		Command testing = new RepeatCommand(4, new ArrayList<Command>(){{
			add(new ForwardCommand(myTurtle,50));
			add(new RightCommand(myTurtle,90));}});
		testing.execute();
//		this.getChildren().addAll(testing());
//		root.getChildren().addAll(this);
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setMaxSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private void testing() {
		Rectangle rect = new Rectangle(20, 20, 100, 100);
		rect.setFill(Color.AQUAMARINE);
		this.getChildren().add(rect);
	}
	
	private void setupTurtle() {
		StackPane sp = new StackPane();
//		sp.getChildren().add(myTurtle);
		sp.setAlignment(Pos.CENTER);
//		sp.translateXProperty().bind(this.widthProperty().subtract(sp.widthProperty()).divide(2));
//		sp.translateYProperty().bind(this.heightProperty().subtract(sp.heightProperty()).divide(2));
		this.getChildren().add(sp);
	}
	
	private void getNewTurtle() {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));
//		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(TEST_IMAGE));
		myTurtle = new Turtle(turtleImage);
		myTurtle.setFitWidth(TURTLE_WIDTH);
		myTurtle.setFitHeight(TURTLE_HEIGHT);
		myTurtle.changeX((INITIAL_WIDTH - TURTLE_WIDTH)/2);
		myTurtle.changeY((INITIAL_HEIGHT - TURTLE_HEIGHT)/2);
		myTurtle.update();
		this.getChildren().add(myTurtle);
	}
	
	
}
	
