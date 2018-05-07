package turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alerts.Alerts;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import observables.Listener;
import view.canvas.DrawingWindow;
/**
 * contains all turtles in a compact manner. This is the main bridge between turtle related items and frontend
 * @author shichengrao
 *
 */
public class TurtleController implements Listener {
	private DrawingWindow myDrawingWindow;
	private List<Turtle> turtles;
	private List<Listener> turtleListeners;
	private double myWidth;
	private double myHeight;
	private double turtleWidth;
	private double turtleHeight;
	private Image myImage;
	private List<Boolean> active;
	private int shapeIndex = 0;
	private int colorIndex = 0;
	private Map<Integer, Color> colors;
	private List<Image> shapes;
	private int currentTurtle;
//	private List<ImageView> stamps;
	private Group stamps;
	
	/**
	 * makes a controller with the given paramters
	 * @param image - default image of all the turtles
	 * @param x - size of canvas in x direction
	 * @param y - size of canvas in y direction
	 * @param width - default width of each turtle
	 * @param height - default height of each turtle
	 * @param dw - drawing window its contained on
	 */
	public TurtleController(Image image, double x, double y, double width, double height, DrawingWindow dw) {
		setupTurtleController(dw);
		myWidth = width;
		myHeight = height;
		turtleWidth = x;
		turtleHeight = y;
		myImage = image;
		addActiveTurtle(image, x, y, width, height);
		notifyListeners();
		currentTurtle = 1;
		stamps = new Group();
		dw.getChildren().add(stamps);
	}
	/**
	 * initializes all the instance variables
	 * @param dw
	 */
	private void setupTurtleController(DrawingWindow dw) {
		myDrawingWindow = dw;
		turtles = new ArrayList<>();
		turtleListeners = new ArrayList<>();
		active = new ArrayList<>();
		setupListener(dw);
	}
	/**
	 * adds a new turtle with given parameters
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void addActiveTurtle(Image image, double x, double y, double width, double height) {
		Turtle newTurtle = new Turtle(image, x, y, width, height);
		newTurtle.addListener(this);
		turtles.add(newTurtle);
		active.add(true);
		notifyListeners();
	}

			
	/**
	 * makes a number of new turtles 
	 * @param num - number of new turtles to make
	 */
	public void makeNewTurtles(int num) {
		for(int i = 0; i < num; i++) {
			addActiveTurtle(myImage, turtleWidth, turtleHeight, myWidth, myHeight);
		}
	}
	
	/**
	 * resets all turtles
	 */
	public void resetTurtles() {
		turtles.stream().forEach(t -> t.resetTurtle());
	}
	/**
	 * sets all to inactive, then activates the correct turtles
	 * @param newindices indices of the turtles to activate, of by one
	 */
	public void resetActiveTurtles(List<Integer> newindices) {
		for(int i = 0; i < turtles.size(); i++) {
			active.set(i, false);
		}
		for(int i = 0; i < newindices.size(); i++) {
			if (newindices.get(i)>=1) {
				active.set(newindices.get(i)-1, true);
			}
		}
		if (getActiveTurtleIndices().size()!=0) {
			currentTurtle = getActiveTurtleIndices().get(0);
		}
		else {
			currentTurtle = 0;
		}
	}
	
	/**
	 * 
	 * @param index
	 * @return turtle at index
	 */
	public Turtle getTurtle(int index) {
		return turtles.get(index);
	}
	/**
	 * 
	 * @return current turtle
	 */
	public int getCurrentTurtleIndex() {
		return currentTurtle;
	}
	/**
	 * sets current turtle to a given index
	 * @param index
	 */
	public void setCurrentTurtleIndex(int index) {
		currentTurtle = index;
	}
	/**
	 * deactivates a given turtle
	 * @param offTurtle
	 */
	public void changeTurtleStatus(Turtle offTurtle) {
		for (int i=0; i<turtles.size(); i++) {
			if (turtles.get(i).equals(offTurtle)) {
				active.set(i, !active.get(i));
				turtles.get(i).setOpaque(active.get(i));
			}
		}
		notifyListeners();
	}
	
	/**
	 * gets the current active turtles
	 * @return list of active turtles
	 */
	public List<Turtle> getActiveTurtles(){
		List<Turtle> temp = new ArrayList<>();
		for(int i = 0; i < turtles.size(); i++) {
			if(active.get(i)) {
				temp.add(turtles.get(i));
			}
		}
		return temp;
	}
	/**
	 * indices of the current active turtles
	 * @return list of indices
	 */
	public List<Integer> getActiveTurtleIndices(){
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < turtles.size(); i++) {
			if (active.get(i)) {
				temp.add(i+1);
			}
		}
		return temp;
	}
	/**
	 * gets information on all turtles
	 * @return list of strings containing the information
	 */
	public List<String> getAllTurtleInfo() {
		List<String> info = new ArrayList<>();
		int index = 1;
		for (Turtle t: turtles) {
			info.add(t.toString(index));
			index +=1;
		}
		return info;
	}
	/**
	 * gets all turtles
	 * @return list of all turtles
	 */
	public List<Turtle> getAllTurtles() {
		return turtles;
	}
	/**
	 * sets up a listener for the controller
	 * @param dw the drawing window
	 */
	public void setupListener(DrawingWindow dw) {
		dw.setupListener(this);
	}
	/**
	 * adds the listener
	 * @param l the listener
	 */
	public void addTurtleListener(Listener l) {
		turtleListeners.add(l);
	}
	/**
	 * updates the listeners
	 */
	private void notifyListeners() {
		for(Listener l: turtleListeners) {
			l.update();
		}
	}
	/**
	 * 
	 * @return number of total turtles
	 */
	public int size() {
		return turtles.size();
	}
	/**
	 * gets the drawing window	
	 * @return
	 */
	public DrawingWindow getDrawingWindow() {
		return myDrawingWindow;
	}
	/**
	 * updates the controller
	 */
	@Override
	public void update() {
		notifyListeners();
	}
    
    /**
     * sets shape corresponding to the index for all active turtles
     * @param index
     */
	 public void setShape(int index) {
		try {
			shapeIndex = index;
			for(int i = 0; i < turtles.size(); i++) {
				if(active.get(i)) {
					turtles.get(i).setShape(shapes.get(index));
				}
			}
		}
		catch(Exception e){
			Alerts.createAlert(new IndexOutOfBoundsException(), "OutOfBounds");
		}
 		
 }
	 /**
	     * sets pen color corresponding to the index for all active turtles
	     * @param index
	     */
	 public void setColorByIndex(int index) {
		 try {
			colorIndex = index;
			for(int i = 0; i < turtles.size(); i++) {
				if(active.get(i)) {
					turtles.get(i).getPen().setColor(colors.get(index));
				}
			}
		 }
 		catch(Exception e){
			Alerts.createAlert(new IndexOutOfBoundsException(), "OutOfBounds");
		}
	 }
	 /**
	  * @return index associated with current shape
	  */
	 public double getShapeIndex() {
		double index = 0;
 		for(int i = 0; i < turtles.size(); i++) {
 			if(active.get(i)) {
 				index = findIndex(turtles.get(i).getImageView().getImage());
 			}
 		}
 		return index;
	 }
	 /**
	  * finds the given image in the saved image list
	  * @param image
	  * @return
	  */
	 public double findIndex(Image image) {
		 for(int i = 0; i < shapes.size(); i++) {
			 if(shapes.get(i).equals(image)) {
				 return i;
			 }
		 }
		 return -1;
	 }
	 /**
	  * @return pen color associated with current shape
	  */
	 public double getColorIndex() {
		 double index = 0;
	 		for(int i = 0; i < turtles.size(); i++) {
	 			if(active.get(i)) {
	 				index = findIndex((Color)turtles.get(i).getPen().getColor());
	 			}
	 		}
	 		return index;
	 }
	 /**
	  * finds the given color in the saved c
	  * @param image
	  * @return
	  */
	 public double findIndex(Color color) {
		 for(Integer key: colors.keySet()) {
			 if(colors.get(key).equals(color)) {
				 return (double)key;
			 }
		 }
		 return 0;
	 }
	 /**
	  * sets the necessary indices and corresponding colors
	  * @param palette the map of ints to colors
	  */
	 public void setPalette(Map<Integer,Color> palette) {
		 colors = palette;
	 }
	 /**
	  * changes the background color
	  * @param index associated with color
	  */
	 public void setBackground(int index) {
		 myDrawingWindow.setBackgroundColor(colors.get(index));
	 }
	 /**
	  * sets the necessary indices and associated images
	  * @param images
	  */
	 public void setImageMenu(List<Image> images) {
		 shapes = images;
	 }
	 
	 
	 public int matchShape(Image img) {
		 for (int i=0; i<shapes.size(); i++) {
			 if (shapes.get(i).equals(img)) {
				 return i;
			 }
		 }
		 return 0;
	 }
	 
	 public double setStamp() {
		int index = 0;
		for(int i = 0; i < turtles.size(); i++) {
			if(active.get(i)) {
				ImageView img = turtles.get(i).makeStamp();
				stamps.getChildren().add(img);
				index = matchShape(img.getImage());
			}
		 }
		return index;
	 }
	 
	 public double clearStamps() {
		 int size = stamps.getChildren().size();
		 System.out.print("here!" + size);
		 stamps.getChildren().clear();
		 if (size > 0) return 1;
		 else return 0;
	 }

}
