package alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import alerts.Resources;

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
}
