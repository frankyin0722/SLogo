package visual_elements;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;


public class Canvas extends Pane {
	public Canvas(Group root) {
		setupInitialCanvas();
		root.getChildren().addAll(this);
	}
	
	private void setupInitialCanvas() {
		String color = "008080";
//		this.setStyle("-fx-background-color: #" + color);
		
		this.setBackground(new Background(new BackgroundFill(Color.web("#" + color), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
}
