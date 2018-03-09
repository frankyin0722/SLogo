package view.canvas;

import javafx.scene.control.ScrollPane;
import slogo_team08.IConstants;
import turtle.Turtle;

public class ScrollingDrawingWindow extends ScrollPane implements IConstants {
	
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
	
	public DrawingWindow getDrawingWindow() {
		return myDrawingWindow;
	}
	
}
