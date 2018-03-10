package turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import observables.Listener;
import view.canvas.DrawingWindow;

public class TurtleController extends Group implements Listener {
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
	private Map<Integer, Paint> colors;
	private Map<Integer, Image> shapes;
	private int currentTurtle;
	public TurtleController(DrawingWindow dw) {
		setupTurtleController(dw);
	}
	
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
	}
	
	private void setupTurtleController(DrawingWindow dw) {
		myDrawingWindow = dw;
		turtles = new ArrayList<>();
		turtleListeners = new ArrayList<>();
		active = new ArrayList<>();
		setupListener(dw);
	}
	
	public void addActiveTurtle(Image image, double x, double y, double width, double height) {
		Turtle newTurtle = new Turtle(image, x, y, width, height);
		newTurtle.addListener(this);
		turtles.add(newTurtle);
		active.add(true);
		notifyListeners();
	}

			
	// makes num new turtles
	public void makeNewTurtles(int num) {
		for(int i = 0; i < num; i++) {
			addActiveTurtle(myImage, turtleWidth, turtleHeight, myWidth, myHeight);
		}
	}
	
	//reset all turtles
	public void resetTurtles() {
		turtles.stream().forEach(t -> t.resetTurtle());
	}
	
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
		else currentTurtle = 0;
		//System.out.println(active);
	}
	
	//get Turtle at index
	public Turtle getTurtle(int index) {
		return turtles.get(index);
	}
	
	public int getCurrentTurtleIndex() {
		return currentTurtle;
	}
	
	public void setCurrentTurtleIndex(int index) {
		currentTurtle = index;
	}
	
	public void changeTurtleStatus(Turtle offTurtle) {
		for (int i=0; i<turtles.size(); i++) {
			if (turtles.get(i).equals(offTurtle)) {
				active.set(i, !active.get(i));
				turtles.get(i).setOpaque(active.get(i));
			}
		}
		notifyListeners();
	}
	
	//get activeTurtles
	public List<Turtle> getActiveTurtles(){
		List<Turtle> temp = new ArrayList<>();
		for(int i = 0; i < turtles.size(); i++) {
			if(active.get(i)) {
				temp.add(turtles.get(i));
			}
		}
		return temp;
	}
	
	public List<Integer> getActiveTurtleIndices(){
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < turtles.size(); i++) {
			if (active.get(i)) {
				temp.add(i+1);
			}
		}
		return temp;
	}
	
	public List<String> getAllTurtleInfo() {
		List<String> info = new ArrayList<>();
		int index = 1;
		for (Turtle t: turtles) {
			info.add(t.toString(index));
			index +=1;
		}
		return info;
	}
	
	public List<Turtle> getAllTurtles() {
		return turtles;
	}
	
	public void setupListener(DrawingWindow dw) {
		dw.setupListener(this);
	}
	
	public void addTurtleListener(Listener l) {
		turtleListeners.add(l);
	}
	private void notifyListeners() {
		for(Listener l: turtleListeners) {
			l.update();
		}
	}

	public int size() {
		return turtles.size();
	}
		
	public DrawingWindow getDrawingWindow() {
		return myDrawingWindow;
	}

	@Override
	public void update() {
		notifyListeners();
	}
    
    
	 public void setShape(int index) {
 		shapeIndex = index;
 		for(Turtle turtle: turtles) {
 			turtle.setShape(shapes.get(index));
 		}
 		//throw error if out of bounds
 }
	 public void setColorByIndex(int index) {
 		colorIndex = index;
 		for(Turtle turtle: turtles) {
 			turtle.getPen().setColor(colors.get(index));
 		}
 		//throw error if oob
	 }
	 public double getShapeIndex() {
 		return shapeIndex;
	 }
 
	 public double getColorIndex() {
 		return colorIndex;
	 }
	 public void setPalette(Map<Integer,Paint> palette) {
		 colors = palette;
	 }
}
