package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 
 * @author elizabethshulman
 * 
 * This class builds upon the BaseButton, using WebView to enable user access to a webpage presenting the default commands.
 */
public class HelpButton extends BaseButton {
	
	private static final String HELP_PAGE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
	
	/**
	 * initializes new BaseButton extension HelpButton
	 */
	public HelpButton() {
		super("Default Commands");
		setupWebLink();
	}


	/**
	 * Initializes this button's action response for when clicked.
	 * Uses WebView() to access online documentation for the commands.
	 */
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