package visual_elements.menu_managers;

import java.util.ResourceBundle;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import turtle.Pen;

public class PenColorMenu extends TitledPane {
	private Pen myPen;
	private ColorPicker cp;
	
	public PenColorMenu(ResourceBundle resources, Pen pen) {
		myPen = pen;
		setupDefaultMenu();
		setupTitledPane();
		setupPenAction();
	}
	
	private void setupTitledPane() {
		this.setMaxWidth(Double.MAX_VALUE);
		this.setText("Change Pen Color");
		this.setContent(cp);
		this.setExpanded(false);
	}
	
	private void setupDefaultMenu() {
		cp = new ColorPicker();
		cp.setValue(Color.BLACK);
		cp.setStyle("-fx-color-label-visible: false ;");
		cp.setMaxWidth(Double.MAX_VALUE);
	}
	
	private void setupPenAction() {
		cp.setOnAction(e -> changePenColor(myPen));
	}
	
	private void changePenColor(Pen pen) {
		pen.setColor(cp.getValue());
	}
	
}
