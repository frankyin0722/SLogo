package command.Turtle;

import command.Command;
import turtle.Turtle;

public class IsShowingCommand implements Command {
	private Turtle myTurtle;
	public IsShowingCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.isVisible()?1:0;
	}
}
