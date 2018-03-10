package turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import observables.Listener;
/**
 * models the logo turtle, contains information about itself and depends on pen
 */
public class Turtle extends Group {
	private Pen pen = new Pen();
	private List<Listener> myListeners;
	private DraggableImageView myImage;
	private double direction = 0;
	private double zeroX;
	private double zeroY;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	/**
	 * makes a turtle with the given information
	 * @param image - visual of the turtle
	 * @param x - max x of screen
	 * @param y - max y of screen
	 * @param width of turtle
	 * @param height of turtle
	 */
	public Turtle(Image image, double x, double y, double width, double height) {
		super();
		zeroX = x- width/2;
		zeroY = y - height/2;
		myImage = new DraggableImageView(this, image);
		myImage.setX(zeroX);
		oldX = 0;
		myImage.setY(zeroY);
		oldY = 0;
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myListeners = new ArrayList<>();
		getChildren().add(myImage);
	}
	/**
	 * default turtle contains no information
	 */
	public Turtle() {
		super();
	}
	 /**
	  * @return the turtle's pen
	  */
    public Pen getPen() {
    		return pen;
    }
    /**
     * updates turtle's position, updates its list of lines if necessary
     */
    public void update() {
    		myImage.setX(newX + zeroX);
    		myImage.setY(newY + zeroY);
    		if(!pen.PenUp()) {
    			Line line = new Line(oldX + zeroX, oldY + zeroY, myImage.getX() + (myImage.getFitWidth() / 2), myImage.getY()+ (myImage.getFitHeight() / 2));
    			pen.update(line);
    			getChildren().add(line);
    		}
    		oldX = newX;
    		oldY = newY;
    		myImage.toFront();
    		notifyListeners();
    }
    /**
     * check the direction the turtle is facing, in radians
     */
    public double getDirection() {
    		return direction;
    }
    /**
     * change the turtle's bearing
     */
    
    public void setDirection(double direction) {
    		this.direction = direction;
    		myImage.setRotate(Math.toDegrees(direction));
    		notifyListeners();

    }
    /**
     * modifies x, remembers old xvalue
     */
    public void changeX(double newX) {
    		oldX = myImage.getX() + (myImage.getFitWidth()/ 2) - zeroX;
    		this.newX = newX;
    }
    /**
     * modifies y, remembers old xvalue
     */
    public void changeY(double newY) {
    		oldY = myImage.getY() + (myImage.getFitHeight()/2) - zeroY;
    		this.newY = newY;
    }
    /**
     * deletes all lines of the turtle
     */
    public void clearLines() {
    		getChildren().clear();
    		getChildren().add(myImage);
    }
    /**
     * @return x coordinate of center of turtle
     */
    public double getX() {
		return myImage.getX() - zeroX;
    }
    /**
     * @return y coordinate of center of turtle
     */
    public double getY() {
		return myImage.getY() - zeroY;
    }
    /**
     * sets the turtle's visibility
     */
    public void setVisibility(boolean visible) {
    		myImage.setVisible(visible);
    		notifyListeners();
    }
    /**
     * checks if turtle is visible
     */
    public boolean checkVisibility() {
    		return myImage.isVisible();
    }
    /**
     * sets the image of the turtle
     */
    public void setImage(Image newImage) {
    		myImage.setImage(newImage);
    }
    public void setFitWidth(double width) {
    		myImage.setFitWidth(width);
    }
    public void setFitHeight(double height) {
    		myImage.setFitHeight(height);
    }
    public void resetTurtle() {
    		newX = 0;
    		newY = 0;
    		pen.setPen(false);
    		direction = 0;
    		myImage.setRotate(Math.toDegrees(direction));
    		update();
    		clearLines();
    }
    
    public String toString(int i) {
    		String result = "";
    		result+="Turtle ID: " + Integer.toString(i) + "\n";
    		result+="Position: (" + oldX + ", " + -oldY + ")" + "\n";
    		result+="Heading: " + Math.toDegrees(direction) + " degrees\n";
    		result+="Visible: " + myImage.isVisible() + "\n";
    		result+= "\n" + pen.toString();
    		return result;
    }
    
    public ImageView getImageView() {
    		return myImage;
    }
    public void setShape(Image image) {
    		myImage.setImage(image);
    }
    
    public void addListener(Listener l) {
    		myListeners.add(l);
    		//pen.addListener(l);
    }
    
    public void notifyListeners() {
    		for (Listener l: myListeners) {
    			l.update();
    		}
    }
    
    public void setOpaque(boolean active) {
		myImage.setOpacity(active?1:0.5);
}

}
