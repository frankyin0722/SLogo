package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.Visualization;

public class TabButton extends Button {
	
	private TabPane myTabs;
	
	public TabButton(TabPane currentView) {
		myTabs = currentView;
		this.setText("+");
		initializeAction();
	}


	private void initializeAction() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				createTab();
			}
		});
	}
	
	public void createTab() {
		Tab newTab = new Tab(
				"Canvas "+(myTabs.getTabs().size()+1));
		newTab.setContent(new Visualization());
		myTabs.getTabs().add(newTab);
		myTabs.getSelectionModel().select(newTab);
	}
}
