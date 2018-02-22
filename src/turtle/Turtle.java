package turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Turtle extends Group {
	private Pen pen = new Pen();
	private ImageView image;
	private double direction = 0;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	private List<Line> lines = new ArrayList<>();
	public Turtle() {
		super();
		getChildren().addAll(lines);
		getChildren().add(image);
	}
	 //returns the turtle's pen
    public Pen getPen() {
    		return pen;
    }

    //updates turtle's position, updates its list of lines if necessary
    public void update() {
    		image.setX(newX);
    		image.setY(newY);
    		if(!pen.PenUp()) {
    			lines.add(new Line(oldX, oldY, image.getX(), image.getY()));
    		}
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
    		oldX = image.getX();
    		this.newX = newX;
    }
    //modifies y, remembers old yvalue
    public void changeY(double newY) {
    		oldX = image.getY();
    		this.newY = newY;
    }
    //deletes all lines of the turtle
    public void clearLines() {
    		lines.clear();
    }
    public double getX() {
		return image.getX();
    }
    public double getY() {
		return image.getX();
    }
    public void setVisibility(boolean visible) {
    		image.setVisible(visible);
    }

}
