package visual_elements.menu_managers;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class LanguageMenu extends TitledPane {
	public static final String LANGUAGE_MENU_KEY = "LanguageMenu";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_RESOURCE_FOLDER = "./src/resources/languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	public ResourceBundle myResources;

	public LanguageMenu() {
		setLanguage(DEFAULT_LANGUAGE);
		setupLanguageMenu();
		setupButtonBox();
//		addObservable();
	}
	
	private void setupLanguageMenu() {
		this.setText(myResources.getString(LANGUAGE_MENU_KEY));
		this.setExpanded(false);
	}
	
	private void setupButtonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		setupAllButtons(buttons);
		this.setContent(buttons);
	}
	
	private void setupAllButtons(VBox vbox) {
		File languageFile = new File(DEFAULT_RESOURCE_FOLDER);
		for (File f: languageFile.listFiles()) {
			if (!f.getName().equals("Syntax.properties")) {
				vbox.getChildren().add(setupButton(f.getName().split(".properties")[0]));
			}
		}
	}
	
	private Button setupButton(String language) {
		Button button = new Button();
		button.setText(language);
		button.setOnAction(e -> setLanguage(language));
		button.setMaxWidth(Double.MAX_VALUE);
		return button;
	}
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
	}

	public ResourceBundle getLanguage() {
		return myResources;
	}
}
