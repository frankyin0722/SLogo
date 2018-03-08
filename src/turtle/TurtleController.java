package turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.Group;
import javafx.scene.image.Image;
import observables.Listener;
import view.canvas.DrawingWindow;

public class TurtleController {
	public static final double INITIAL_WIDTH = 700;
	public static final double INITIAL_HEIGHT = 500;
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
	public static final String DEFAULT_IMAGE = "cute_turtle.png";
	
	private DrawingWindow myDrawingWindow;
	private List<Turtle> turtles;
	private List<Listener> turtleListeners;
	private double myWidth;
	private double myHeight;
	private double turtleWidth;
	private double turtleHeight;
	private DrawingWindow turtlePane;
	private Image image;
	private List<Boolean> active;
	
	public TurtleController(DrawingWindow dw) {
		setupTurtleController(dw);
		addDefaultTurtle();
		addTestTurtle();
	}
	
	public TurtleController(Image image, double x, double y, double width, double height, DrawingWindow dw) {
		setupTurtleController(dw);
		myWidth = width;
		myHeight = height;
		turtleWidth = x;
		turtleHeight = y;
		addActiveTurtle(image, x, y, width, height);
		addTestTurtle();
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
		turtles.add(newTurtle);
		active.add(true);
		notifyListeners();
	}
	
	private Turtle defaultTurtle() {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE));
		return new Turtle(turtleImage, INITIAL_WIDTH/2, INITIAL_HEIGHT/2, TURTLE_WIDTH, TURTLE_HEIGHT);
	}
	
	private Turtle testTurtle() {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE));
		return new Turtle(turtleImage, 2500-100, 2500-100, TURTLE_WIDTH, TURTLE_HEIGHT);
	}
		
	private void addDefaultTurtle() {
		Turtle defaultTurtle = defaultTurtle();
		turtles.add(defaultTurtle);
		active.add(true);
		notifyListeners();
	}
	
	private void addTestTurtle() {
		Turtle testTurtle = testTurtle();
		turtles.add(testTurtle);
		active.add(true);
		notifyListeners();

	}
			
	// makes num new turtles
	public void makeNewTurtles(int num) {
		IntStream.range(0,num)
			.forEach( i -> {
				System.out.println("new turtle created");
				Turtle newTurtle = new Turtle(image, turtleWidth, turtleHeight, myWidth, myHeight);
				System.out.println("making turtle step 1");
				turtles.add(newTurtle);
				System.out.println("making turtle step 2");
				active.add(true);
				turtlePane.getChildren().add(newTurtle);
				System.out.println("making turtle step 3");
				System.out.println("turtlemade");
			});
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
		System.out.println(active);
	}
	
	//get Turtle at index
	public Turtle getTurtle(int index) {
		return turtles.get(index);
	}
	
	public void changeTurtleStatus(Turtle offTurtle) {
		for (int i=0; i<turtles.size(); i++) {
			if (turtles.get(i).equals(offTurtle)) {
				active.set(i, !active.get(i));
			}
		}
		notifyListeners();
	}
	
	//get activeTurtles
	public List<Turtle> getActiveTurtles(){
		List<Turtle> temp = new ArrayList<Turtle>();
		for(int i = 0; i < turtles.size(); i++) {
			if(active.get(i)) {
				temp.add(turtles.get(i));
			}
		}
		return temp;
	}
	
	public List<Integer> getActiveTurtleIndices(){
		List<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < turtles.size(); i++) {
			if (active.get(i)) {
				temp.add(i+1);
			}
		}
		return temp;
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
}
