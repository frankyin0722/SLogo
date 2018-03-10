package view.vis_elements;

import buttons.TabButton;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import slogo_team08.IConstants;
import view.supplements.TabDragFunctionality;
/**
 * TabView initializes new workspace for each tab, similar
 * to implementing a different Engine per workspace
 * Each tab has its own active turtles, settings, etc.
 * @author elizabethshulman
 *
 */
public class TabView implements IConstants {

	private TabPane tabs;
	private AnchorPane myAnchor;
	private Scene myScene;
	/**
	 * Constructor creates pane for tabs
	 */
	public TabView() {
		initializePanes();
	}
	/**
	 * Method initializes tab pane configurations
	 * Allows for new tab, changing tab ordering (drag and drop)
	 * Each tabs is workspace independent of other tabs
	 */
	private void initializePanes() {
		tabs = new TabPane();
		TabDragFunctionality dragsupport = new TabDragFunctionality(tabs);
		myAnchor = new AnchorPane();
		TabButton plusButton = new TabButton(tabs);
		AnchorPane.setRightAnchor(plusButton, 10.0);
		plusButton.createTab();
		myAnchor.getChildren().addAll(tabs, plusButton);
		myScene = new Scene(myAnchor,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
		myAnchor.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
	}
	/**
	 * Method to
	 * @return current scene
	 * Distinguishes between different tabs
	 */
	public Scene getScene() {
		return myScene;
	}
}
