package view.canvas;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;
import view.IVisualConstants;

public class DrawingWindow extends Pane implements IVisualConstants {

//	public static final String IMAGE_PATH = "./images/";
//	public static final String FRANKLIN_IMAGE = "franklin.jpg";
//	public static final String TURTLE_IMAGE = "cute_turtle.png";
	
	private Turtle myTurtle;

	public DrawingWindow() {
		setupInitialCanvas();
//		setupTurtle();
//		myTurtle.changeX(myTurtle.getX() + 300);
		initializeColorPicker();
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INTERNAL_CANVAS_WIDTH, INTERNAL_CANVAS_HEIGHT);
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		this.setBackgroundColor(INITIAL_COLOR);
	}
	
	private void setupTurtle() {

		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE));
		myTurtle = new Turtle(turtleImage, INTERNAL_CANVAS_WIDTH / 2, INTERNAL_CANVAS_HEIGHT / 2, TURTLE_WIDTH, TURTLE_HEIGHT);
		this.getChildren().add(myTurtle);
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
	
	public Turtle getDefaultTurtle() {
		return myTurtle;
	}
	
}
	
