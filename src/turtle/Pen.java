package turtle;

import javafx.scene.paint.Color;

public class Pen {
	private Color color;
	private boolean up;
	
	public Pen() {
		this(Color.BLACK, false);
	}
	public Pen(Color c, boolean up) {
		color = c;
		this.up = up;
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

}
