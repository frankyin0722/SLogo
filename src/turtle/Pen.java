package turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pen {
	private Color color;
	private boolean up;
	private double width;
	public Pen() {
		this(Color.BLACK, false, 1);
	}
	public Pen(Color c, boolean up, double width) {
		color = c;
		this.up = up;
		this.width = width;
	}
	//returns if the pen is up
    public boolean PenUp() {
    		return up;
    }

    //sets pen to up for true, down for false
    public void setPen(boolean penUp) {
    		up = penUp;
    }

    //sets pen color
    public void setColor(Color color) {
    		this.color = color;
    }
    
    //changes the width of the pen
    public void setWidth(double newWidth) {
    		width = newWidth;
    }
    //make a line
    public void update(Line line) {
    		line.setStrokeWidth(width);
    		line.setStroke(color);
    }
    //prints current state
    @Override
    public String toString() {
    		String result = "";
    		result+="Pen Properties:\n";
    		result+="Color: " + color + "\n";
    		result+="Width: " + width + "\n";
    		result+="Pen Up? " + (up?"Yes":"No") + "\n";
    		return result;
    }
}
