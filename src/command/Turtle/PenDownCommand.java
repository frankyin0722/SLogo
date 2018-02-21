package command.Turtle;

import command.Command;
import turtle.Turtle;

public class PenDownCommand implements Command{
	private Turtle myTurtle;
	
	public PenDownCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		myTurtle.getPen().setPen(false);
		return 0;
	}
	
}
