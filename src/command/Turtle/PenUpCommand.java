package command.Turtle;

import command.Command;
import turtle.Turtle;

public class PenUpCommand implements Command{
	private Turtle myTurtle;
	
	public PenUpCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		myTurtle.getPen().setPen(true);
		return 1;
	}
	
}
