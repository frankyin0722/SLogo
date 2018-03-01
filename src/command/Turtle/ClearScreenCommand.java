package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * clears the screen
 */
public class ClearScreenCommand implements Command{
	private Turtle myTurtle;
	
	public ClearScreenCommand(Turtle turtle){
		myTurtle = turtle;
	}
	/**
	 * resets turtle location, deletes all lines 
	 */
	public double execute(){
		double delta = Math.sqrt(Math.pow(myTurtle.getX(), 2)+Math.pow(myTurtle.getY(), 2));
		myTurtle.changeX(0);
		myTurtle.changeY(0);
		myTurtle.update();
		myTurtle.clearLines();
		return delta;
	}
	
}
