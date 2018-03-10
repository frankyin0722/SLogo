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
 * 
 * @author elizabethshulman
 *
 * This class works closely with the CustomVarsMenu class. It creates a new Stage to manage the
 * creation and modifaction of custom variables in the IDE.
 */
public class StageVariableChanger extends Stage {

	private TextField newInfo;
	private Button submit;
	private String type;
	private String prevKey;
	private CustomVarsMenu myBase;
	private StageVariableChanger currentStage;
	
	/**
	 * Initialize a StageVariableChanger.
	 * @param keyOrVal        keyword determining whether a variable Key or Value must be updated
	 * @param key			 Key corresponding to the variable prior to modification requests
	 * @param customVarsMenu  menu containing the variables to be modified
	 */
	public StageVariableChanger(String keyOrVal, String key, CustomVarsMenu customVarsMenu) {
		type = keyOrVal;
		prevKey = key;
		myBase = customVarsMenu;
		currentStage = this;
		buildScene();
		initializeFormat();
	}

	/**
	 * Builds and sets the scene for this stage, containing a submission button and a text field.
	 */
	private void buildScene() {
		HBox varsChanger = new HBox();
		newInfo = new TextField();
		submit = new Button("Update " + type);
		varsChanger.getChildren().addAll(newInfo, submit);
		this.setScene(new Scene(varsChanger));
	}

	/**
	 * Specifies functionality for submit button and format for Stage
	 */
	private void initializeFormat() {
		setSubmitFunctionality();
		this.centerOnScreen();
		this.show();
	}

	/**
	 * Calls for the appropriate response to TextField input, depending on if the text should update a key or a value
	 */
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

	/**
	 * Updates the key value for the variable
	 */
	private void keyResponse() {
		try {
			myBase.getVariableManager().changeKey(prevKey, ":" + newInfo.getText());
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR, "Invalid Key format", ButtonType.OK);
			alert.showAndWait();
		}
	}
	
	/**
	 * Updates the value of a variable
	 */
	private void valResponse() {
		try {
			myBase.getVariableManager().setVariable(Double.parseDouble(newInfo.getText()), prevKey);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "New variable must be of type Double", ButtonType.OK);
			alert.showAndWait();
		}
	}
}