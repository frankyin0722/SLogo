package slogo_team08;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.vis_elements.TabView;

/**
 * 
 * @author elizabethshulman
 *
 * This class coordinates the scene generation and stage setting necessary for launching the simulation.
 */
public class Engine {

	public Engine(Stage myStage) {
		TabView slogoView = new TabView();
		Scene myScene = slogoView.getScene();
		myStage.setScene(myScene);		
		myStage.show();	
		}
}
