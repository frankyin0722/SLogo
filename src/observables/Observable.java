package observables;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Observable {
	private ObservableList<Observer> observableList;
	private boolean changed;
	
	public Observable() {
		setupObservableList();
		clearChanged();
	}
	
	protected void setupObservableList() {
		observableList = FXCollections.observableList(new ArrayList<>());
	}

	public void addObserver(Observer obs) {
		observableList.add(obs);
	}

//	public void deleteObserver(Observer obs);
//	public void deleteObservers();

	protected void setChanged() {
		changed = true;
	}

	protected void clearChanged() {
		changed = false;
	}
	public boolean hasChanged() {
		return (changed);
	}

	public void notifyObservers(Object obj) {
		if (hasChanged()) {
			for (Observer observer: observableList) {
				observer.update(this, obj);
			}
		}
	}


}
