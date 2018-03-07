package view;


import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoTop extends VBox{
	public InfoTop() {
		setupTitle();
		
	}
	
	private void setupTitle() {
		Text t = new Text("SLogo Interpreter");
		this.setAlignment(Pos.CENTER);
		t.setStyle(
			"-fx-font: 40px Tahoma;" +
			"-fx-font-weight: bold;" +
			"-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, deepskyblue 0%, red 100%);"
			);
		this.getChildren().add(t);
	}
}
