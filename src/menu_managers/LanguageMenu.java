package menu_managers;

import java.util.ResourceBundle;

import buttons.ChineseButton;
import buttons.ClearButton;
import buttons.EnglishButton;
import buttons.ResetButton;
import buttons.RunButton;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class LanguageMenu extends TitledPane {
	public static final String LANGUAGE_MENU_KEY = "LanguageMenu";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	
	public ResourceBundle myResources;
	private EnglishButton myEnglishButton;
	private ChineseButton myChineseButton;

	public LanguageMenu() {
		changeLanguage(DEFAULT_LANGUAGE);
		setupLanguageMenu();
		setupButtonBox();
	}
	
	private void setupLanguageMenu() {
		this.setText(myResources.getString(LANGUAGE_MENU_KEY));
	}
	
	private void setupButtonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		myEnglishButton = new EnglishButton();
		myChineseButton = new ChineseButton();
		addButton(buttons, myEnglishButton);
		addButton(buttons, myChineseButton);
		this.setContent(buttons);
	}
	
	private void addButton(VBox buttonBox, Button button) {
		button.setOnAction(e -> changeLanguage(button.getClass().getSimpleName().split("Button")[0]));
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
		
	private void changeLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	public ResourceBundle getLanguage() {
		return myResources;
	}
}
