package command.Turtle;

import turtle.Turtle;

public class HeadingCommand {
	private Turtle myTurtle;
	public HeadingCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getDirection();
	}
}
