package buttons;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import alerts.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.CommandWindow;

public class LoadFileButton extends Button {
	public LoadFileButton() {
		this.setText("Load File");
	}
	
	public void loadFile(CommandWindow cw) {
		this.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						FileChooser fc = new FileChooser();
						Stage stage = new Stage();
						fc.setInitialDirectory(new File("./data/examples"));
						fc.setTitle("Open Resource File");
						File file = fc.showOpenDialog(stage);
						if (file != null) {
							openFile(cw, file);
						}
						try {
							Alerts.XMLRead(file.getCanonicalPath());
						} catch (IOException e) {
							Alerts.createAlert(new NullPointerException(), "filler");
						}
					}
			});
		}
	
	private void openFile(CommandWindow cw, File file) {
		for (String s: readFile(file)) {
			cw.addText(s);
		}
    }
	
	private List<String> readFile(File file) {
		List<String> lines = new ArrayList<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				lines.add(line + "\n");
			}
			br.close();
			return lines;
		} catch (IOException e) {
			System.err.print("Could not read file");
		}
		return null;
	}
	
}




