package visual_elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TextFieldButtons extends HBox{
	private Button myRunButton;
	private Button myClearButton;
	
	public TextFieldButtons() {
		setupRunButton();
		setupClearButton();
	}
	
	private void setupRunButton() {
		myRunButton = new Button("Run");
		this.setAlignment(Pos.TOP_RIGHT);
		this.getChildren().add(myRunButton);
	}
	
	private void setupClearButton() {
		myClearButton = new Button("Clear");
		this.setAlignment(Pos.BOTTOM_RIGHT);
		this.getChildren().add(myClearButton);
	}
	
	
	public Button getRunButton() {
		return myRunButton;
	}
	
	public Button getClearButton() {
		return myClearButton;
	}
}
