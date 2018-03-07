package visual_elements;

import javafx.scene.control.ScrollPane;
import turtle.Turtle;

public class ScrollingDrawingWindow extends ScrollPane{
	private DrawingWindow myDrawingWindow;
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
//		this.setPrefViewportWidth(100);
//		this.setPrefViewportHeight(100);
		
	}
		
//	private void setupScrolling() {
//	
//	}
	
	public Turtle getDefaultTurtle() {
		return myDrawingWindow.getDefaultTurtle();
	}
	
	public DrawingWindow getDrawingWindow() {
		return myDrawingWindow;
	}
	
}
