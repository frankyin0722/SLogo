package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * puts pen up, so no lines are made when the turtle moves
 */
public class PenUpCommand implements Command{
	private Turtle myTurtle;
	
	public PenUpCommand(Turtle turtle){
		myTurtle = turtle;
	}
	/**
	 * sets pen up, returns 1
	 */
	public double execute(){
		myTurtle.getPen().setPen(true);
		return 1;
	}
	
}
