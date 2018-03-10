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

public class HBoxPenChanger extends HBox {

	private Pen ourPen;
	private int index;
	
	public HBoxPenChanger() {
		Text titleText = new  Text("      ID        Color       Pen Width");
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
				buildWidthStage());
	}
	
	private Text buildTurtleLabel() {
		Text turtleIndex = new Text("Pen " + Integer.toString(index+1) + " ");
		turtleIndex.setFont(new Font(14));
		return turtleIndex;
	}
	
	private ColorPicker buildColorSelection() {
		ColorPicker recolorer = new ColorPicker(ourPen.getColor());
		recolorer.setStyle("-fx-color-label-visible: false ;");
		recolorer.setOnAction(e -> ourPen.setColor(recolorer.getValue()));
		return recolorer;
	}
	
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
