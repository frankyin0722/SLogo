package turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pen {
	private Color color;
	private boolean up;
	private double width;
	public Pen() {
		this(Color.BLACK, false, 2);
	}
	public Pen(Color c, boolean up, double width) {
		color = c;
		this.up = up;
		width = 1;
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

    //returns color of the pen
    public Color getColor() {
    		return color;
    }
    
    //changes the width of the pen
    public void setWidth(double newWidth) {
    		width = newWidth;
    }
    //make a line
    public Line update(Line line) {
    		line.setStrokeWidth(width);
    		line.setStroke(color);
    		return line;
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
