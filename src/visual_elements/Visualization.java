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
		this.setCenter(new Canvas(root));
		root.getChildren().add(this);
//		this.setBottom(new CommandWindow());
	}
	
}
