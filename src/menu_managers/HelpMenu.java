package menu_managers;

import buttons.HelpButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpMenu extends VBox {
	private HelpButton myHelpButton;
	public HelpMenu() {
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		myHelpButton = new HelpButton();
		myHelpButton.setOnAction(
		        new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		                Stage stage = new Stage();
		                VBox helpBox = new VBox(20);
		                helpBox.getChildren().add(new HelpMenuContent());
		                Scene dialogScene = new Scene(helpBox, 300, 200);
		                stage.setScene(dialogScene);
		                stage.show();
		            }
		      });
		myHelpButton.setMaxWidth(Double.MAX_VALUE);
		this.getChildren().add(myHelpButton);
	}
}
