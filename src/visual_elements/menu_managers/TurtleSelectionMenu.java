package visual_elements.menu_managers;

import java.awt.Checkbox;
import java.awt.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import turtle.Turtle;
import visual_elements.DrawingWindow;
public class TurtleSelectionMenu extends VBox {
	public static final double INITIAL_WIDTH = 700;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
	public static final String DEFAULT_IMAGE = "cute_turtle.png";

	public List activeTurtles;
	private DoubleProperty myHomeWidth;
	private DoubleProperty myHomeHeight;
	public TurtleSelectionMenu(DrawingWindow dw) {
		setupMenu();
		setupHomeLocation(dw);
		addTurtle(dw);
	}
	
	private void setupMenu() {
		this.getChildren().add(new Text("Active Turtles"));
		this.setPadding(new Insets(12, 12, 12, 12));
//		this.setStyle();
	}
	
	private void setupHomeLocation(DrawingWindow dw) {
//		myHomeWidth.bind(dw.widthProperty().divide(2));
//		myHomeHeight.bind(dw.heightProperty().divide(2));
	}
	
	private void addTurtle(DrawingWindow dw) {
		HBox hbox = new HBox();
		StackPane sp = new StackPane();
		CheckBox cb = new CheckBox();
		Turtle turtle = defaultTurtle();
		
		sp.getChildren().add(turtle.getImageView());
		hbox.getChildren().addAll(cb, sp);
//		hbox.getChildren().add(turtle);
		dw.getChildren().add(turtle);
		this.getChildren().add(hbox);
	}
	
	private Turtle defaultTurtle() {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE));
		return new Turtle(turtleImage, INITIAL_WIDTH / 2, INITIAL_HEIGHT / 2, TURTLE_WIDTH, TURTLE_HEIGHT);
	}
}
