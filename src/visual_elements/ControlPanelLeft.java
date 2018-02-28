package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import turtle.Turtle;
import visual_elements.menu_managers.HistoryMenu;
import visual_elements.menu_managers.PenColorMenu;
import visual_elements.menu_managers.TurtleMenu;

public class ControlPanelLeft extends VBox {
//	private TurtleMenu myTurtleMenu;
	private Turtle myTurtle;
	public ControlPanelLeft(Turtle turtle, ResourceBundle resources) {
		myTurtle = turtle;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
		this.getChildren().addAll(new HistoryMenu(resources), 
				new TurtleMenu(resources, myTurtle), 
				new PenColorMenu(resources, myTurtle.getPen()));
	}
}
