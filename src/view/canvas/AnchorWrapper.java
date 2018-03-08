package view.canvas;

import javafx.scene.layout.AnchorPane;

public class AnchorWrapper extends AnchorPane {

	public AnchorWrapper() {
		
		this.getChildren().add(new ScrollingDrawingWindow());
		
	}
}
