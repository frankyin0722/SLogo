package turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.image.Image;
import view.canvas.DrawingWindow;

public class TurtleController {
	private List<Turtle> turtles;
	private double myWidth;
	private double myHeight;
	private double turtleWidth;
	private double turtleHeight;
	private DrawingWindow turtlePane;
	private Image image;
	private List<Boolean> active;
	
	public TurtleController(Image image, double x, double y, double width, double height, DrawingWindow internalCanvas) {
		myWidth = width;
		myHeight = height;
		turtleWidth = x;
		turtleHeight = y;
		turtles = new ArrayList<Turtle>();
		active = new ArrayList<Boolean>();
		turtlePane = internalCanvas;
		turtlePane.getChildren().addAll(turtles);
		makeNewTurtles(1);
	}
	
	// makes num new turtles
	public void makeNewTurtles(int num) {
		IntStream.range(0,num)
			.forEach( i -> {
				Turtle newTurtle = new Turtle(image, turtleWidth, turtleHeight, myWidth, myHeight);
				turtles.add(newTurtle);
				turtlePane.getChildren().add(newTurtle);
				active.add(true);
				System.out.println("turtlemade");
			});
	}
	
	//reset all turtles
	public void resetTurtles() {
		turtles.stream().forEach(t -> t.resetTurtle());
	}
	
	//get Turtle at index
	public Turtle getTurtle(int index) {
		return turtles.get(index);
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
	
}
