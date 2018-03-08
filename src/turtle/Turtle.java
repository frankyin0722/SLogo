package turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Turtle extends Group {
	private Pen pen = new Pen();
	private ImageView myImage;
	private double direction = 0;
	private double zeroX;
	private double zeroY;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	private List<Color> colors = new ArrayList<Color>() {{
		add(Color.GREEN);
		add(Color.GREY);
	}};
	private List<Image> images = new ArrayList<Image>();
	private int shapeIndex = 0;
	private int colorIndex = 0;
	public Turtle(Image image, double x, double y, double width, double height) {
		super();
		zeroX = x- width/2;
		zeroY = y - height/2;
		myImage = new ImageView(image);
		myImage.setX(zeroX);
		oldX = 0;
		myImage.setY(zeroY);
		oldY = 0;
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		getChildren().add(myImage);
	}
	public Turtle() {
		super();
	}
	 //returns the turtle's pen
    public Pen getPen() {
    		return pen;
    }

    //updates turtle's position, updates its list of lines if necessary
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
    		oldX = myImage.getX() + (myImage.getFitWidth()/ 2) - zeroX;
    		this.newX = newX;
    		System.out.println("x" + this.newX);
    }
    //modifies y, remembers old yvalue
    public void changeY(double newY) {
    		oldY = myImage.getY() + (myImage.getFitHeight()/2) - zeroY;
    		this.newY = newY;
    		System.out.println("y" + this.newY);
    }
    //deletes all lines of the turtle
    public void clearLines() {
    		getChildren().clear();
    		getChildren().add(myImage);
    }
    public double getX() {
		return myImage.getX() - zeroX;
    }
    public double getY() {
		return myImage.getY() - zeroY;
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
    public void resetTurtle() {
    		newX = 0;
    		newY = 0;
    		pen.setPen(false);
    		update();
    		clearLines();
    }
    @Override
    public String toString() {
    		String result = "";
    		result+="Turtle Properties:\n";
    		result+="Position: (" + oldX + ", " + oldY + ")" + "\n";
    		result+="Heading: " + Math.toDegrees(direction) + "\n";
    		result+= pen.toString();
    		return result;
    }
    
    public double getShapeIndex() {
    		return shapeIndex;
    }
    
    public double getColorIndex() {
    		return colorIndex;
    }
    
    public ImageView getImageView() {
    		return myImage;
    }
    public void setShape(int index) {
    		shapeIndex = index;
    		myImage.setImage(images.get(index));
    		//throw error if out of bounds
    }
    public void setColorByIndex(int index) {
    		colorIndex = index;
    		pen.setColor(colors.get(index));
    		//throw error if oob
    }
    
}
