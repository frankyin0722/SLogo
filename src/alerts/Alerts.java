package alerts;
//modified from cell society
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {
	public static void createAlert(Exception e, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(Resources.getString("ErrorTitle"));
        alert.setHeaderText(e.getMessage());
        alert.setContentText(String.format(Resources.getString(message)));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
    }
	public static void XMLCreated(String filepath) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Resources.getString("XMLSuccess"));
		alert.setContentText("filler");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
	}
	public static void XMLRead(String filepath) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Resources.getString("XMLSuccess2"));
		alert.setContentText("filler");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
	}
}
