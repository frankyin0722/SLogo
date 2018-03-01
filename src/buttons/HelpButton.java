package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpButton extends Button {
	
	private static final String HELP_PAGE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
	public HelpButton() {
		
		this.setText("Default Commands");
		setupWebLink();
	}


private void setupWebLink() {
	this.setOnAction(
	        new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                Stage stage = new Stage();
		        	   	WebView web = new WebView();
		        	   	web.getEngine().load(HELP_PAGE);
		        	   	Scene scene = new Scene(web);
		        	   	stage.setScene(scene);
		        	   	stage.show();
	            }
	      });
	this.setMaxWidth(Double.MAX_VALUE);
	}
}