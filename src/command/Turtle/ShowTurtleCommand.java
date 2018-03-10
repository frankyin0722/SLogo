package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * shows a turtle
 */
public class ShowTurtleCommand implements Command{
	private Turtle myTurtle;
	
	public ShowTurtleCommand(Turtle turtle){
		myTurtle = turtle;
	}
	/**
	 * sets the visibility of the turtle to true, returns 1
	 */
	public double execute(){
		myTurtle.setVisibility(true);
		return 1;
	}
	
}
