package turtle;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
/**
 * represents a pen that each turtle holds one of. Holds its color, thickness, and if its down or not
 * @author shichengrao
 *
 */
public class Pen {
	private Paint color;
	private boolean up;
	private double width;
//	private List<Listener> myListeners;
	/**
	 * defaults to a down black pen with 1 width if no parameters are specified
	 */
	public Pen() {
		this(Color.BLACK, false, 1);
	}
	/**
	 * creates custom pen off of parameters
	 * @param c -color of pen
	 * @param up- whether pen is up or not
	 * @param width -pen width
	 */
	public Pen(Paint c, boolean up, double width) {
		color = c;
		this.up = up;
		this.width = width;
	}
	
	/**
	 * @return if the pen is up
	 */
    public boolean PenUp() {
    		return up;
    }

    /**
     * sets pen to up for true, down for false
     */
    public void setPen(boolean penUp) {
    		up = penUp;
    		//notifyListeners();

    }
    /**
     * 
     * @return the width of the pen
     */
    public double getWidth() {
    		return width;
    }

    /**
     * sets pen color
     */
    public void setColor(Paint color) {
    		this.color = color;
    		//notifyListeners();

    }
    
  
    
    
    /**
     * sets pen width
     */
    public void setWidth(double newWidth) {
    		width = newWidth;
    		//notifyListeners();

    }
    
    /**
     * updates a line 
     */
    public void update(Line line) {
    		line.setStrokeWidth(width);
    		line.setStroke(color);
    		//notifyListeners();
    }
    

    
    /**
     * prints the pen in a readable manner
     */
    @Override
    public String toString() {
    		String result = "";
    		result+="Pen Properties:\n";
    		result+="Color: " + color + "\n";
    		result+="Width: " + width + "\n";
    		result+="Pen Up? " + (up?"Yes":"No") + "\n";
    		return result;
    }
    /**
     * 
     * @return the color of the pen
     */
    public Paint getColor() {
    		return color;
    }

    
    
    //SPEAKER METHODS
    
//    public void addListener(Listener l) {
//		myListeners.add(l);
//}
//
//    public void notifyListeners() {
//		for (Listener l: myListeners) {
//			l.update();
//		}
//    }
//    
//	@Override
//	public void removeListener(Listener l) {
//		myListeners.remove(l);
//	}

}
