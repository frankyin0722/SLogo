package menu_managers;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import buttons.ChineseButton;
import buttons.ClearButton;
import buttons.EnglishButton;
import buttons.ResetButton;
import buttons.RunButton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import observables.CollectionsDemo;

public class LanguageMenu extends TitledPane {
	public static final String LANGUAGE_MENU_KEY = "LanguageMenu";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	
	public ResourceBundle myResources;
	private EnglishButton myEnglishButton;
	private ChineseButton myChineseButton;

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
		myEnglishButton = new EnglishButton();
		myChineseButton = new ChineseButton();
		addButton(buttons, myEnglishButton);
		addButton(buttons, myChineseButton);
		this.setContent(buttons);
	}
	
	private void addButton(VBox buttonBox, Button button) {
		button.setOnAction(e -> changeLanguage(button));
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
		
	private void changeLanguage(Button button) {
		setLanguage(button.getClass().getSimpleName().split("Button")[0]);
		
	}
	
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void addObservable() {
//        List<ResourceBundle> list = new ArrayList<>();
//        list.add(myResources);
//	    ObservableList<ResourceBundle> observableList = FXCollections.observableList(list);
//	    
//	    observableList.addListener(new ListChangeListener() {
//            @Override
//            public void onChanged(ListChangeListener.Change change) {
//                System.out.print(observableList.get(0));
//            }
//        });
//	}
	
	public ResourceBundle getLanguage() {
		return myResources;
	}
}
