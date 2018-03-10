package view.supplements;

import buttons.BaseButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import turtle.Pen;
import turtle.Turtle;

/**
 * 
 * @author elizabethshulman
 * 
 * This class works closely in conjunction with PenMenu.java. It builds an HBox that will
 * serve as a row in a PenMenu instance, allowing a user to modify aspects of pen behavior 
 * for an individual turtle in one setting.
 * Its primary elements currently are the ID/index (indicating with which turtle it belongs),
 * the ColorPicker (which allows a user to recolor the pen), and the Slider (enabled via a
 * pop-up stage; allows a user to modify the pen thickness).
 */
public class HBoxPenChanger extends HBox {

	private Pen ourPen;
	private int index;
	
	/**
	 * Instantiate a new generic HBoxPenChanger object, to be used as a ListView header specifying
	 * elements to be included in the turtle-specific HBoxPenChangers.
	 */
	public HBoxPenChanger() {
		Text titleText = new  Text("      ID        Color       Pen Width");
		titleText.setFont(new Font(14));
		titleText.setFill(Color.DARKBLUE);
		this.getChildren().add(titleText);
	}
	
	/**
	 * Instantiate an HBoxPenChanger for a turtle.
	 * @param t  turtle holding pen to be modified
	 * @param i  ID a user may use to reference turtle t  
	 */
	public HBoxPenChanger(Turtle t, int i) {
		ourPen = t.getPen();
		index = i;
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(
				buildTurtleLabel(),
				buildColorSelection(),
				buildWidthStage());
	}
	
	/**
	 * Builds the string defining to which turtle this pen belongs
	 * @return string specifying pen ID
	 */
	private Text buildTurtleLabel() {
		Text turtleIndex = new Text("Pen " + Integer.toString(index+1) + " ");
		turtleIndex.setFont(new Font(14));
		return turtleIndex;
	}
	
	/**
	 * Builds a ColorPicker enabling a user to change the color of a turtle's pen
	 * @return ColorPicker with activated event handler
	 */
	private ColorPicker buildColorSelection() {
		ColorPicker recolorer = new ColorPicker((Color) ourPen.getColor());
		recolorer.setStyle("-fx-color-label-visible: false ;");
		recolorer.setOnAction(e -> ourPen.setColor(recolorer.getValue()));
		return recolorer;
	}
	
	/**
	 * Builds a pop-up stage containing a slider, allowing a user to modify a pen's thickness
	 * @return Button that cues stage creation
	 */
	private Button buildWidthStage() {
		Button b = new BaseButton("Modify Width");
		b.setOnAction(e -> {
			Stage addSlider = new StagePenChanger(ourPen);
			addSlider.showAndWait();
		});
		b.setAlignment(Pos.CENTER_RIGHT);
		return b;
	}
}
