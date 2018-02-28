package visual_elements.menu_managers;

import buttons.HelpButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CustomVarsMenu extends VBox {
	private HelpButton myHelpButton;
	public CustomVarsMenu() {
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		myHelpButton = new HelpButton();
		myHelpButton.setOnAction(
		        new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		                Stage stage = new Stage();
			        	   	WebView web = new WebView();
			        	   	web.getEngine().load("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php");
			        	   	Scene scene = new Scene(web);
			        	   	stage.setScene(scene);
			        	   	stage.show();
		            }
		      });
		myHelpButton.setMaxWidth(Double.MAX_VALUE);
		this.getChildren().add(myHelpButton);
	}
}
