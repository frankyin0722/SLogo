package observables;

import javafx.beans.value.ObservableValue;

public class LanguageObserver implements Observer {
	@SuppressWarnings("rawtypes")
	private ObservableValue ov = null;
	public LanguageObserver(@SuppressWarnings("rawtypes") ObservableValue ov) {
		this.ov = ov;
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		System.out.print("At LanguaugeObserver");
		System.out.print(obj.toString());
		System.out.print(obs.hasChanged());
//		obs = obj;
		
	}




}
