package view;

import javafx.scene.control.ScrollPane;
import turtle.Turtle;

public class ScrollingDrawingWindow extends ScrollPane {
	private DrawingWindow myDrawingWindow;
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
	}
	
	public Turtle getDefaultTurtle() {
		return myDrawingWindow.getDefaultTurtle();
	}
	
}
