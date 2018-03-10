package buttons;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import slogo_team08.IConstants;
import view.vis_elements.Visualization;

public class LanguageChangeButton extends Button implements IConstants {

	private ResourceBundle myResources;
	private Visualization vis;
	Stage languageView;

	public LanguageChangeButton(Visualization visualization) {
		vis = visualization;
		setLanguage(DEFAULT_LANGUAGE);
		this.setText(myResources.getString(LANGUAGE_MENU_KEY));
		this.setOnAction(e -> {
			languageView = new Stage();
			languageView.setScene(new Scene(setupButtonView()));
			languageView.showAndWait();
		});
		this.setMaxWidth(Double.MAX_VALUE);
	}
	
	private ListView<Button> setupButtonView() {
		ListView<Button> buttonDisplay = new ListView<>();
		buttonDisplay.setItems(FXCollections.observableArrayList(setupAllButtons()));
		return buttonDisplay;
	}

	private ArrayList<Button> setupAllButtons() {
		ArrayList<Button> buttonList = new ArrayList<>();
		File languageFile = new File(DEFAULT_LANGUAGE_FOLDER);
		for (File f: languageFile.listFiles()) {
			if (!f.getName().equals("Syntax.properties")) {
				buttonList.add(setupButton(f.getName().split(".properties")[0]));
			}
		}
		return buttonList;
	}
	
	private BaseButton setupButton(String language) {
		BaseButton button = new BaseButton(language);
		button.setOnAction(e -> {
			setLanguage(language);
			languageView.close();
		});
		return button;
	}
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PACKAGE + language);
		vis.setLanguage(language);
	}
	
	public ResourceBundle getLanguage() {
		return myResources;
	}
}
