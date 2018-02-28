package observables;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Speaker {
	private ObservableList<Listener> observableList;
	private boolean changed;
	
	public Speaker() {
		setupObservableList();
	}
	
	protected void setupObservableList() {
		observableList = FXCollections.observableList(new ArrayList<>());
	}

	public void addListener(Listener l) {
		observableList.add(l);
	}

	public void removeListener(Listener l) {
		observableList.remove(l);
	}

	public void notifyListeners() {
	}


}
