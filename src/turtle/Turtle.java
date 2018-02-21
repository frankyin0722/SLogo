package turtle;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Turtle extends ImageView {
	private Pen pen;
	private double direction;
	private double oldX;
	private double oldY;
	public Turtle() {
		pen = new Pen();
		direction = 0;
	}
	 //returns the turtle's pen
    public Pen getPen() {
    		return pen;
    }

    //updates turtle's position, returns line that should be drawn, or null if no line should be drawn
    public Line update() {
    		if(!pen.PenUp()) {
    			return new Line(oldX, oldY, getX(), getY());
    		}
    		return null;
    }

    //check the direction the turtle is facing in radians
    public double getDirection() {
    		return direction;
    }

    //change the turtle's bearing
    public void setDirection(double direction) {
    		this.direction = direction;
    }
    //modifies x, remembers old xvalue
    public void changeX(double newX) {
    		oldX = getX();
    		setX(newX);
    }
    //modifies y, remembers old yvalue
    public void changeY(double newY) {
    		oldX = getY();
    		setX(newY);
    }

}
