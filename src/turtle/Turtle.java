package turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Turtle extends Group {
	private Pen pen = new Pen();
	private ImageView myImage;
	private double direction = 0;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	private List<Line> lines = new ArrayList<>();
	public Turtle(Image image) {
		super();
		myImage = new ImageView(image);
		getChildren().addAll(lines);
		getChildren().add(myImage);
	}
	 //returns the turtle's pen
    public Pen getPen() {
    		return pen;
    }

    //updates turtle's position, updates its list of lines if necessary
    public void update() {
    		myImage.setX(newX);
    		myImage.setY(newY);
    		if(!pen.PenUp()) {
    			lines.add(new Line(oldX, oldY, myImage.getX(), myImage.getY()));
    		}
    }

    //check the direction the turtle is facing in radians
    public double getDirection() {
    		return direction;
    }

    //change the turtle's bearing
    public void setDirection(double direction) {
    		this.direction = direction;
    		myImage.setRotate(Math.toDegrees(direction));
    }
    //modifies x, remembers old xvalue
    public void changeX(double newX) {
    		oldX = myImage.getX();
    		myImage.setX(newX);
    		this.newX = newX;
    }
    //modifies y, remembers old yvalue
    public void changeY(double newY) {
    		oldX = myImage.getY();
    		myImage.setY(newY);
    		this.newY = newY;
    }
    //deletes all lines of the turtle
    public void clearLines() {
    		lines.clear();
    }
    public double getX() {
		return myImage.getX();
    }
    public double getY() {
		return myImage.getX();
    }
    public void setVisibility(boolean visible) {
    		myImage.setVisible(visible);
    }
    public void setImage(Image newImage) {
    		myImage.setImage(newImage);
    }
    public void setFitWidth(double width) {
    		myImage.setFitWidth(width);
    }
    public void setFitHeight(double height) {
    		myImage.setFitHeight(height);
    }
}
