package view.canvas;

import javafx.scene.control.ScrollPane;
import slogo_team08.IConstants;
/**
 * ScrollPane that hosts internal canvas (DrawingWindow)
 * Allows large internal canvas to be viewed with scrolling.
 * Allows turtles to travel in wider range than current screen display. 
 * Leaves flexibility for future zoom function implementation
 * @author xlany, elizabethshulman
 *
 */
public class ScrollingDrawingWindow extends ScrollPane implements IConstants {
	private DrawingWindow myDrawingWindow;
	/**
	 * Constructor that creates new internal canvas
	 * and sets up ScrollPane
	 */
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	/**
	 * Method to initialize scrolling window
	 * Always show scrolling bars.
	 * Sets view to the center of internal canvas,
	 * as turtle is initialized in center
	 * 
	 */
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		myDrawingWindow.setManaged(false);
		this.setHvalue(0.5);
		this.setVvalue(0.5);
		this.setPrefSize(EXTERNAL_CANVAS_WIDTH, EXTERNAL_CANVAS_HEIGHT);
	}
	/**
	 * Method to
	 * @return internal canvas DrawingWindow
	 */
	public DrawingWindow getInternalCanvas() {
		return myDrawingWindow;
	}
	
}
