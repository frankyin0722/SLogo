package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * hides a turtle
 */
public class HideTurtleCommand implements Command{
	private Turtle myTurtle;
	
	public HideTurtleCommand(Turtle turtle){
		myTurtle = turtle;
	}
	/**
	 * sets the visibility of the turtle to false, returns 0
	 */
	public double execute(){
		myTurtle.setVisibility(false);
		return 0;
	}
	
}
