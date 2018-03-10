package buttons;

import interpreter.CommandTreeInterpreter;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import view.canvas.DrawingWindow;

public class CanvasColorButton extends BaseButton {

	private DrawingWindow currentCanvas;
	
	public CanvasColorButton(CommandTreeInterpreter interpreter) {
		super("Change Canvas Color");
		currentCanvas = interpreter.getTurtleController().getDrawingWindow();
		this.setOnAction(e -> initializeWindow());
	}

	private void initializeWindow() {
		Stage colorSelection = new Stage();
		colorSelection.setScene(new Scene(initializeColorPicker()));
		colorSelection.show();
	}

	private ColorPicker initializeColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setOnAction(e -> currentCanvas.setBackgroundColor(colorPicker.getValue()));
		return colorPicker;
	}
}