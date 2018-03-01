package visual_elements.menu_managers;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import buttons.HelpButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpMenu extends TitledPane {
	public static final String COMMANDS_WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
	public HelpMenu() {
		setupHelpMenu();
		setupContent();
	}
	
	
//	private void setupHelpMenu() {
//		myHelpButton = new HelpButton();
//		myHelpButton.setOnAction(
//		        new EventHandler<ActionEvent>() {
//		            @Override
//		            public void handle(ActionEvent event) {
//		                Stage stage = new Stage();
//			        	   	WebView web = new WebView();
//			        	   	web.getEngine().load("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php");
//			        	   	Scene scene = new Scene(web);
//			        	   	stage.setScene(scene);
//			        	   	stage.show();
//		            }
//		      });
//		myHelpButton.setMaxWidth(Double.MAX_VALUE);
//		this.getChildren().add(myHelpButton);
//	}
	
	private void setupHelpMenu() {
		this.setText("Help");
		this.setExpanded(false);
	}
	
	private void setupContent() {
		VBox vbox = new VBox();
		vbox.setFillWidth(true);
//		setupWebLink(vbox);
		setupCustomVars(vbox);
		this.setContent(vbox);
	}
	
	private void setupWebLink(VBox vbox) {
		Button commands = new Button();
		commands.setText("Basic Commands");
		commands.setOnAction(e -> showWebsite());
		vbox.getChildren().add(commands);
	}

	
	private void showWebsite() {
		openWebpage(COMMANDS_WEBSITE);
	}
	
	public void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "URLMessageError");
	    }
	}
	
//	public static boolean openWebpage(URI uri) {
//	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
//	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
//	        try {
//	            desktop.browse(uri);
//	            return true;
//	        } catch (Exception e) {
//	    			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "URLMessageError");
//	        }
//	    }
//	    return false;
//	}
//
//	public static boolean openWebpage(URL url) {
//	    try {
//	        return openWebpage(url.toURI());
//	    } catch (URISyntaxException e) {
//			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "URLMessageError");
//	    }
//	    return false;
//	}
}
