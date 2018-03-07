package view.canvas;

import javafx.scene.control.ScrollPane;
import turtle.Turtle;
import view.IVisualConstants;

public class ScrollingDrawingWindow extends ScrollPane implements IVisualConstants {
	
	private DrawingWindow myDrawingWindow;
	
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);

		myDrawingWindow.setManaged(false);
		this.setHvalue(0.5);
		this.setVvalue(0.5);
		this.setPrefSize(EXTERNAL_CANVAS_WIDTH, EXTERNAL_CANVAS_HEIGHT);
	}
	
//	public Turtle getDefaultTurtle() {
//		return myDrawingWindow.getDefaultTurtle();
//	}
	
	public DrawingWindow getInternalCanvas() {
		return myDrawingWindow;
	}
	
}
