package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * turns turtle right
 */
public class RightCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public RightCommand(double movement, Turtle turtle){
		myTurtle = turtle;
		myMovement = Math.toRadians(movement);
	}
	/**
	 * turns the turtle right by movement, returns degrees turned
	 */
	public double execute(){
		myTurtle.setDirection(myTurtle.getDirection() + myMovement);
		return myMovement;
	}
	
}
