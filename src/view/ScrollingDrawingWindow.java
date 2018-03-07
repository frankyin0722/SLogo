package view;

import javafx.scene.control.ScrollPane;
import turtle.Turtle;

public class ScrollingDrawingWindow extends ScrollPane {
	
	private DrawingWindow myDrawingWindow;
	public static final double INITIAL_WIDTH = 695;
	public static final double INITIAL_HEIGHT = 500;
	
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		myDrawingWindow.setPrefSize(5000,5000);
		myDrawingWindow.setManaged(false);
		this.setPrefSize(INITIAL_WIDTH, INITIAL_HEIGHT);
	}
	
	public Turtle getDefaultTurtle() {
		return myDrawingWindow.getDefaultTurtle();
	}
	
}
