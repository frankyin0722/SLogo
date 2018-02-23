package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetTowardsCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public SetTowardsCommand(Turtle turtle, double x, double y){
		myTurtle = turtle;
		myMovement = y < 0? Math.atan(x/y) + Math.PI: Math.atan(x/y);
	}
	
	public double execute(){
		double delta = Math.abs(Math.toDegrees(myMovement));
		myTurtle.setDirection(myMovement);
		return delta;
	}
	
}
