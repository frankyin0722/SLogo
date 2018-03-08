package view.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import turtle.Pen;
import turtle.Turtle;

public class PenColorMenu extends TitledPane {
	private List<Pen> myPens;
	private ColorPicker cp;
	
	public PenColorMenu(ResourceBundle resources, List<Turtle> turtles) {
		myPens = getActivePens(turtles);
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
		cp.setOnAction(e -> changePenColor(myPens));
	}
	
	private void changePenColor(List<Pen> pens) {
		for (Pen p: pens) {
			p.setColor(cp.getValue());
		}
	}
	
	public List<Pen> getActivePens(List<Turtle> turtles) {
		List<Pen> pens = new ArrayList<Pen>();
		for (Turtle t: turtles) {
			pens.add(t.getPen());
		}
		return pens;
	}
	
}
