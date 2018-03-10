package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * Puts pen of turtle down, so lines are made when the turtle moves
 */
public class PenDownCommand implements Command{
	private Turtle myTurtle;
	
	public PenDownCommand(Turtle turtle){
		myTurtle = turtle;
	}
	/**
	 * sets pen down, returns 0
	 */
	public double execute(){
		myTurtle.getPen().setPen(false);
		return 0;
	}
	
}
