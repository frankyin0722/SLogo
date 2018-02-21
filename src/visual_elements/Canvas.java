package visual_elements;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Turtle;
import javafx.scene.layout.Pane;


public class Canvas extends Pane {
	Turtle myTurtle;
	public Canvas(Group root) {
		setupInitialCanvas();
		root.getChildren().addAll(this, testing());
	}
	
	private void setupInitialCanvas() {
		String color = "008080";
//		this.setStyle("-fx-background-color: #" + color);
		this.setBackground(new Background(new BackgroundFill(Color.web("#" + color), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private Rectangle testing() {
		Rectangle rect = new Rectangle(20, 20, 200, 200);
		rect.setFill(Color.RED);
		return rect;
	}
	
//	private void setupTurtle(Group root) {
//		
//	}
	
}
