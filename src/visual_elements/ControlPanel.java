package visual_elements;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlPanel extends GridPane {

	public ControlPanel() {
		
		this.add(new Button(), 0, 0);
		this.add(new Button(), 0, 1);
		this.add(new Button(), 0, 2);
	
	}
	
}
