package visual_elements;

import javafx.scene.Scene;
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
	public Visualization(Scene scene) {
		this.setCenter(new Canvas());
//		this.setBottom(new CommandWindow());
	}
}
