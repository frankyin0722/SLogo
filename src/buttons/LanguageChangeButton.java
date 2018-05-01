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

/**
 * 
 * @author elizabethshulman, xlany
 * 
 * This class enables a user to change the language of inputted commands. It creates a button,
 * which upon click opens a new pop-up list of buttons (one representing each available language).
 * 
 */
public class LanguageChangeButton extends Button implements IConstants {

	private ResourceBundle myResources;
	private Visualization vis;
	Stage languageView;

	/**
	 * Initialize a LanguageChangeButton, creating a new pop-up window upon click
	 * @param visualization 	the Visualization object that requires knowledge of language changes
	 */
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
	
	/**
	 * Presents a list of buttons with the JavaFX ListView layout
	 * @return 	ListView presentation of buttons
	 */
	private ListView<Button> setupButtonView() {
		ListView<Button> buttonDisplay = new ListView<>();
		buttonDisplay.setItems(FXCollections.observableArrayList(setupAllButtons()));
		return buttonDisplay;
	}

	/**
	 * Traverses through resource files and creates one new button for each valid language
	 * @return	list of buttons, each representing a different language
	 */
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
	
	/**
	 * Initializes a language-specific button, setting the IDE's language to change upon selection
	 * @param language
	 * @return button with initiated event handlers
	 */
	private BaseButton setupButton(String language) {
		BaseButton button = new BaseButton(language);
		button.setOnAction(e -> {
			setLanguage(language);
			languageView.close();
		});
		return button;
	}
	
	/**
	 * Accesses Visualization's languages bundle to enable other IDE objects to change language (e.g. parser)
	 * @param language 	Selected language to be used in reading commands
	 */
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PACKAGE + language);
		vis.setLanguage(language);
	}
	
	/**
	 * Retrieves myResources, the current LanguageBundle to be used in reading commands
	 * @return ResourceBundle containing language information
	 */
	public ResourceBundle getLanguage() {
		return myResources;
	}
}
