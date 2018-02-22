package visual_elements;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import visual_elements.user_input_presentation.CommandContainer;

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
		this.setCenter(new DrawingWindow(root));
		this.setBottom(new CommandContainer());
		this.setRight(new ControlPanel());
		root.getChildren().add(this);
	}
	
}
