package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * made the turtle face in some absolute direction
 */
public class SetHeadingCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public SetHeadingCommand(double movement, Turtle turtle){
		myTurtle = turtle;
		myMovement = Math.toRadians(movement);
	}
	/**
	 * sets turtle direction to movement, returns degrees moved
	 */
	public double execute(){
		double delta = Math.abs(Math.toDegrees(myTurtle.getDirection() - myMovement));
		myTurtle.setDirection(myMovement);
		return delta;
	}
	
}
