package visual_elements;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class DrawingWindow extends Pane {
	public static final int INITIAL_WIDTH = 900;
	public static final int INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;

//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
	private Turtle myTurtle;
	private Pane drawingWindow;

	public DrawingWindow() {
		drawingWindow = this;
		setupInitialCanvas();
		getNewTurtle();
		initializeColorPicker();
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setMaxSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private void getNewTurtle() {
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
				drawingWindow.setBackground(new Background( 
						new BackgroundFill(colorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		this.getChildren().add(colorPicker);
	}
	
}
	
