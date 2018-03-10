package slogo_team08;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.vis_elements.TabView;
public class Engine {
	
	public Engine(Stage myStage) {
		TabView slogoView = new TabView();
		Scene myScene = slogoView.getScene();
		myStage.setScene(myScene);		
		myStage.show();	
		}
}