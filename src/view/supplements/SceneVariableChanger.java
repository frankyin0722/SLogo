package view.supplements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.menus.CustomVarsMenu;

/**
 * user can enter double value variable name
 * @author elizabethshulman
 *
 */
public class SceneVariableChanger extends Stage {

	private TextField newInfo;
	private Button submit;
	private String type;
	private String prevKey;
	private CustomVarsMenu myBase;
	private SceneVariableChanger currentStage;
	
	public SceneVariableChanger(String keyOrVal, String key, CustomVarsMenu customVarsMenu) {
		type = keyOrVal;
		prevKey = key;
		myBase = customVarsMenu;
		currentStage = this;
		buildScene();
		initializeFormat();
	}

	private void buildScene() {
		HBox varsChanger = new HBox();
		newInfo = new TextField();
		submit = new Button("Update " + type);
		varsChanger.getChildren().addAll(newInfo, submit);
		this.setScene(new Scene(varsChanger));
	}

	private void initializeFormat() {
		setSubmitFunctionality();
		this.centerOnScreen();
		this.show();
	}

	private void setSubmitFunctionality() {
		submit.setOnAction(
				new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent click) {
						if(type.equalsIgnoreCase("key")) {
							keyResponse();
						} else if(type.equalsIgnoreCase("value")) {
							valResponse();
						}
						myBase.update();
						currentStage.close();
					}
					
				});
	}

	private void keyResponse() {
		try {
			myBase.getVariableManager().changeKey(prevKey, ":" + newInfo.getText());
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR, "Invalid Key format", ButtonType.OK);
			alert.showAndWait();
		}
	}
	
	private void valResponse() {
		try {
			myBase.getVariableManager().setVariable(Double.parseDouble(newInfo.getText()), prevKey);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "New variable must be of type Double", ButtonType.OK);
			alert.showAndWait();
		}
	}
}