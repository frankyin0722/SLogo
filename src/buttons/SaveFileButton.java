package buttons;

import java.io.File;

import interpreter.CommandTreeInterpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveFileButton extends Button {
	public SaveFileButton() {
		this.setText("Save");
	}
	public void save(CommandTreeInterpreter interpreter) {
		this.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						FileChooser fc = new FileChooser();
						Stage stage = new Stage();
						fc.setInitialDirectory(new File("./data/examples"));
						fc.setTitle("Open Resource File");
						File file = fc.showSaveDialog(stage);
					}
			});
	}
}
