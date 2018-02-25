package menu_managers;

import java.util.ResourceBundle;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import turtle.Pen;

public class PenColorMenu extends ColorPicker {
	private Pen myPen;
	
	public PenColorMenu(ResourceBundle resources, Pen pen) {
		myPen = pen;
		setupDefaultMenu();
		setupPenAction();
	}
	
	private void setupDefaultMenu() {
		this.setValue(Color.BLACK);
		this.setStyle("-fx-color-label-visible: false ;");
	}
	
	private void setupPenAction() {
		this.setOnAction(e -> changePenColor(myPen));
	}
	
	private void changePenColor(Pen pen) {
		pen.setColor(this.getValue());
	}
	
}
