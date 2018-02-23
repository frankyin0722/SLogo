package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RunButton extends Button {

	public RunButton(TextArea textbox) {
		super("Run");
		act(textbox);
	}
	
	private void act(TextArea textbox) {
		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textbox.clear();
			}
			
		});
	}
}
