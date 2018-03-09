package view.vis_elements;

import buttons.TabButton;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import slogo_team08.IVisualConstants;

public class TabView implements IVisualConstants {

	private TabPane tabs;
	private AnchorPane myAnchor;
	private Scene myScene;

	public TabView() {
		initializePanes();
	}
	
	private void initializePanes() {
		tabs = new TabPane();
		TabDragFunctionality dragsupport = new TabDragFunctionality();
		dragsupport.addSupport(tabs);
		myAnchor = new AnchorPane();
		TabButton plusButton = new TabButton(tabs);
		AnchorPane.setRightAnchor(plusButton, 10.0);
		plusButton.createTab();
		myAnchor.getChildren().addAll(tabs, plusButton);
		myScene = new Scene(myAnchor,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
		myAnchor.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
	}

	
	public Scene getScene() {
		return myScene;
	}
}
