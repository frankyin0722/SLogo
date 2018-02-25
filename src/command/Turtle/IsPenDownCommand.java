package command.Turtle;

import command.Command;
import turtle.Turtle;

public class IsPenDownCommand implements Command{
	private Turtle myTurtle;
	public IsPenDownCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getPen().PenUp() ? 0: 1;
	}
}
