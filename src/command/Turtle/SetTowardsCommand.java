package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * turns a turtle towards a given point
 */
public class SetTowardsCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	/**
	 * turns turtle towards a given point, returns degrees turned
	 */
	public SetTowardsCommand(double x, double y, Turtle turtle){
		myTurtle = turtle;
		myMovement = Math.atan2(x - turtle.getX(), y + turtle.getY());
	}
	
	public double execute(){
		double delta = Math.abs(Math.toDegrees(myMovement));
		myTurtle.setDirection(myMovement);
		return delta;
	}
	
}
