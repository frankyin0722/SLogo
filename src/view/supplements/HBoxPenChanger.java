package view.supplements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import turtle.Pen;
import turtle.Turtle;

public class HBoxPenChanger extends HBox {

	private Pen ourPen;
	private int index;
	
	public HBoxPenChanger() {
		Text titleText = new  Text(" ID    Color             Pen Width");
		titleText.setFont(new Font(14));
		titleText.setFill(Color.DARKBLUE);
		this.getChildren().add(titleText);
	}
	
	public HBoxPenChanger(Turtle t, int i) {
		ourPen = t.getPen();
		index = i;
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(
				buildTurtleLabel(),
				buildColorSelection(),
				buildWidthSlider());
	}
	
	//BUILD TEXT STRING
	private Text buildTurtleLabel() {
		Text turtleIndex = new Text(Integer.toString(index+1));
		turtleIndex.setFont(new Font(15));
		return turtleIndex;
	}
	
	//BUILD COLORPICKER
	private ColorPicker buildColorSelection() {
		ColorPicker recolorer = new ColorPicker(ourPen.getColor());
		recolorer.setStyle("-fx-color-label-visible: false ;");
		recolorer.setOnAction(e -> ourPen.setColor(recolorer.getValue()));
		return recolorer;
	}
	
	/**
	 * Initializes a slider to change the width of a turtle's pen.
	 * Code modified from  <a href="https://docs.oracle.com/javafx/2/ui_controls/slider.htm">JavaFX Documentation</a>
	 * @return slider object to modify turtle's pen width
	 */
	private Slider buildWidthSlider() {
		Slider slider = new Slider(0,2,1);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
	                Number old_val, Number new_val) {
				ourPen.setWidth(new_val.doubleValue());
			}
		});
		return slider;
	}
}
