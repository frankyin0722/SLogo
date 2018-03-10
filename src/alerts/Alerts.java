package alerts;
/**
 * this code is for creating popups. It is adapted from my cellsociety project
 */
/**
 * Class created to make specific alerts for errors that may be thrown throughout the program
 */
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {
	/**
     * Generic create alert method that allows for only one method to be written here,
     * and creates flexibility so that a user simply only needs to add in new string values in the resourceKeys folder
     * instead of having to write an entire new method ontop of doing that.
     * @param e
     * @param message
     */
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
	/**
     * Alert to notify user that the XML file has been successfully created and saved
     * @param filePath
     */
	public static void XMLCreated(String filepath) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Resources.getString("XMLSuccess"));
		alert.setContentText("File created and saved");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
	}
	/**
     * Alert to notify user that the XML file has been successfully read
     * @param filePath
     */
	public static void XMLRead(String filepath) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Resources.getString("XMLSuccess2"));
		alert.setContentText("file successfully read");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
	}
}
