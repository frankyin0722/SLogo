package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetHeadingCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public SetHeadingCommand(double movement, Turtle turtle){
		myTurtle = turtle;
		myMovement = Math.toRadians(movement);
	}
	
	public double execute(){
		double delta = Math.abs(Math.toDegrees(myTurtle.getDirection() - myMovement));
		myTurtle.setDirection(myMovement);
		return delta;
	}
	
}
