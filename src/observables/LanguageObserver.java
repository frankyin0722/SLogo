package observables;

import javafx.beans.value.ObservableValue;

public class LanguageObserver implements Listener {
	@SuppressWarnings("rawtypes")
	private ObservableValue ov = null;
	public LanguageObserver(@SuppressWarnings("rawtypes") ObservableValue ov) {
		this.ov = ov;
	}
	
	@Override
	public void update(Speaker s) {

		
	}




}
