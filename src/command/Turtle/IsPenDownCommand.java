package command.Turtle;

import turtle.Turtle;

public class IsPenDownCommand {
	private Turtle myTurtle;
	public IsPenDownCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getPen().PenUp() ? 0: 1;
	}
}
