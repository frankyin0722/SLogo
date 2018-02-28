package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetTowardsCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
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
