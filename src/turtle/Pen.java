package turtle;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import observables.Listener;
import observables.Speaker;

public class Pen {
	private Paint color;
	private boolean up;
	private double width;
//	private List<Listener> myListeners;
	
	public Pen() {
		this(Color.BLACK, false, 1);
	}
	
	public Pen(Paint c, boolean up, double width) {
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
    		//notifyListeners();

    }

    //sets pen color
    public void setColor(Paint color) {
    		this.color = color;
    		//notifyListeners();

    }
    
    public double getWidth() {
    		return width;
    }
    
    
    //changes the width of the pen
    public void setWidth(double newWidth) {
    		width = newWidth;
    		//notifyListeners();

    }
    
    //make a line
    public void update(Line line) {
    		line.setStrokeWidth(width);
    		line.setStroke(color);
    		//notifyListeners();
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
