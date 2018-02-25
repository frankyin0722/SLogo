package visual_elements;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class DrawingWindow extends Pane {
	public static final double INITIAL_WIDTH = 1000;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	
	private Turtle myTurtle;

	public DrawingWindow() {
		setupInitialCanvas();
		setupTurtle();
		
		myTurtle.changeX(myTurtle.getX() + 300);
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
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
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
	
