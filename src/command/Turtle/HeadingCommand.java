package command.Turtle;

import command.Command;
import turtle.Turtle;

public class HeadingCommand implements Command{
	private Turtle myTurtle;
	public HeadingCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getDirection();
	}
}
