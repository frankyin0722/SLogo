package visual_elements;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Turtle;

public class DrawingWindow extends Pane {
	public static final int INITIAL_WIDTH = 600;
	public static final int INITIAL_HEIGHT = 400;
	public static final int TURTLE_WIDTH = 20;
	public static final int TURTLE_HEIGHT = 30;

//	public static final String IMAGE_PATH = "./images/";
	public static final String FRANKLIN_IMAGE = "franklin.jpg";
	public static final String TURTLE_IMAGE = "cute_turtle.png";
//	public static final String TEST_IMAGE = "./images/cute_turtle.png";
	private Turtle myTurtle;

	public DrawingWindow(Group root) {
		setupInitialCanvas();
		
		this.getChildren().addAll(testing(), setupTurtle());
		root.getChildren().addAll(this);
	}
	
	private void setupInitialCanvas() {
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	
	
	private Rectangle testing() {
		Rectangle rect = new Rectangle(20, 20, 100, 100);
		rect.setFill(Color.RED);
		return rect;
	}
	
	private Turtle setupTurtle() {

		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));
//		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(TEST_IMAGE));
		Turtle turtle = new Turtle();
		turtle.setImage(turtleImage);
		turtle.setFitWidth(TURTLE_WIDTH);
		turtle.setFitHeight(TURTLE_HEIGHT);
		turtle.setX((INITIAL_WIDTH - TURTLE_WIDTH)/2);
		turtle.setY((INITIAL_HEIGHT - TURTLE_HEIGHT)/2);
		return turtle;
	}
}
