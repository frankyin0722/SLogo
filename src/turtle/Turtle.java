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
	public Turtle(Image image, double x, double y, double width, double height) {
		super();
		myImage = new ImageView(image);
		myImage.setX(x);
		myImage.setY(y);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
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
    			getChildren().add(new Line(oldX, oldY, myImage.getX(), myImage.getY()));
    		}
    		oldX = newX;
    		oldY = newY;
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
    		this.newX = newX;
    }
    //modifies y, remembers old yvalue
    public void changeY(double newY) {
    		oldY = myImage.getY();
    		this.newY = newY;
    }
    //deletes all lines of the turtle
    public void clearLines() {
    		getChildren().clear();
    		getChildren().add(myImage);
    }
    public double getX() {
		return myImage.getX();
    }
    public double getY() {
		return myImage.getY();
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
