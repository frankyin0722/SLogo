package observables;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LanguageObservable extends Observable {
	private ObservableList<Observer> observableList;
	private ResourceBundle myResources;
	boolean changed;
	public LanguageObservable(ResourceBundle resources) {
		super();
		setResources(resources);
	}
	
	public void changeResources(ResourceBundle resources) {
		setResources(resources);
		setChanged();
		notifyObservers(resources);
		clearChanged();
	}
	
	public void setResources(ResourceBundle resources) {
		myResources = resources;
	}
	
	


}
