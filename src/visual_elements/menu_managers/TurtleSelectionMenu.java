package visual_elements.menu_managers;

import java.awt.Checkbox;
import java.awt.List;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import turtle.Turtle;
import visual_elements.DrawingWindow;
public class TurtleSelectionMenu extends VBox {
	public List activeTurtles;
	private IntegerProperty myHomeWidth;
	private IntegerProperty myHomeHeight;
	public TurtleSelectionMenu(DrawingWindow dw) {
		setupMenu();
		setupHomeLocation(dw);
		addTurtle(dw);
	}
	
	private void setupMenu() {
		this.getChildren().add(new Text("Active Turtles"));
		this.setPadding(new Insets(12, 12, 12, 12));
//		this.setStyle();
	}
	
	private void setupHomeLocation(DrawingWindow dw) {
//		myHomeWidth.bind(dw.widthProperty().divide(2));
//		myHomeHeight.bind(dw.heightProperty().divide(2));
	}
	
	private void addTurtle(DrawingWindow dw) {
		HBox hbox = new HBox();
		CheckBox cb = new CheckBox();
		Turtle turtle = new Turtle();
		hbox.getChildren().add(cb);
		hbox.getChildren().add(turtle);
		dw.getChildren().add(turtle);
		this.getChildren().add(hbox);
	}
}
