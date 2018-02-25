package visual_elements;

import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import menu_managers.HistoryMenu;
import menu_managers.PenColorMenu;
import menu_managers.TurtleMenu;
import turtle.Turtle;

public class ControlPanelLeft extends VBox {
//	private TurtleMenu myTurtleMenu;
	private Turtle myTurtle;
	public ControlPanelLeft(Turtle turtle, ResourceBundle resources) {
		myTurtle = turtle;
		initializeMenus(resources);
	}

	private void initializeMenus(ResourceBundle resources) {
//		myTurtleMenu = new TurtleMenu(myTurtle);
		this.getChildren().addAll(new HistoryMenu(resources), 
				new TurtleMenu(resources, myTurtle), 
				new PenColorMenu(resources, myTurtle.getPen()));
	}
}
