package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.vis_elements.Visualization;

/**
 * 
 * @author elizabethshulman
 * 
 * This class enables a user to create a new workspace within the TabPane. It is conceptually
 * modeled after standard web browsers.
 */
public class TabButton extends Button {
	
	private TabPane myTabs;
	
	/**
	 * Instantiate a new button for creating new tabs.
	 */
	public TabButton(TabPane currentView) {
		myTabs = currentView;
		this.setText("+");
		initializeAction();
	}


	/**
	 * Initialize a new-tab button's response to being clicked by calling for new tab creation
	 */
	private void initializeAction() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				createTab();
			}
		});
	}
	
	/**
	 * Creates and places a new tab in the TabPane
	 */
	public void createTab() {
		Tab newTab = new Tab(
				"Canvas "+(myTabs.getTabs().size()+1));
		newTab.setContent(new Visualization());
		myTabs.getTabs().add(newTab);
		myTabs.getSelectionModel().select(newTab);
	}
}
