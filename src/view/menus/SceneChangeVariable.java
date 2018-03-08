package view.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import variables.Variable;

public class SceneChangeVariable extends Stage {

	private TextField newInfo;
	private Button submit;
	private String type;
	private String prevKey;
	private Variable prevVal;
	private CustomVarsMenu myBase;
	private SceneChangeVariable currentStage;
	
	public SceneChangeVariable(String keyOrVal, String key, CustomVarsMenu customVarsMenu) {
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
							myBase.getVariableManager().changeKey(prevKey, ":" + newInfo.getText());
						} else if(type.equalsIgnoreCase("val")) {
							System.out.println(newInfo.getText());
							System.out.println(prevKey);
							try {
								myBase.getVariableManager().setVariable(Double.parseDouble(newInfo.getText()), prevKey);
							} catch (NumberFormatException e) {
								Alert alert = new Alert(AlertType.ERROR, "New variable must be of type Double", ButtonType.OK);
								alert.showAndWait();
							}
						}
						myBase.update();
						currentStage.close();
					}
				});
	}

}
