package visual_elements;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class Visualization extends BorderPane {
	/**
	 * @author elizabethshulman
	 * @author xlany
	 * @param scene
	 * 
	 * Initializes and arranges each of the elements within the scene
	 * 
	 */

	public Visualization(Group root) {
		initializeVis(root);
	}

	private void initializeVis(Group root) {
		this.setCenter(new DrawingWindow());
		this.setBottom(new TextFieldInput());
		this.setRight(new ControlPanelRight());
		this.setLeft(new ControlPanelLeft());
		
		this.setWidth(getMaxWidth());
		
		root.getChildren().add(this);
	}
	
}
