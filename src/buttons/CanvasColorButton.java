package buttons;

import interpreter.CommandTreeInterpreter;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import view.canvas.DrawingWindow;

/**
 * 
 * @author elizabethshulman
 * 
 * This class enables a user to change the color of the standard canvas,
 * upon which the turtles move
 */
public class CanvasColorButton extends BaseButton {

	private DrawingWindow currentCanvas;
	
	/**
	 * Create instance of ColorCanvasButton, using
	 * @param interpreter to access the necessary TurtleController and canvas.
	 */
	public CanvasColorButton(CommandTreeInterpreter interpreter) {
		super("Change Canvas Color");
		currentCanvas = interpreter.getTurtleController().getDrawingWindow();
		this.setOnAction(e -> initializeWindow());
	}

	/**
	 * Calls for Stage creation, using ColorPicker as a root node.
	 */
	private void initializeWindow() {
		Stage colorSelection = new Stage();
		colorSelection.setScene(new Scene(initializeColorPicker()));
		colorSelection.show();
	}

	/**
	 * Builds the ColorPicker and sets color selection to change canvas color
	 * @return ColorPicker to change the canvas color
	 */
	private ColorPicker initializeColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setOnAction(e -> currentCanvas.setBackgroundColor(colorPicker.getValue()));
		return colorPicker;
	}
}