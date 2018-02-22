package command.Turtle;

import turtle.Turtle;

public class IsShowingCommand {
	private Turtle myTurtle;
	public IsShowingCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.isVisible()?1:0;
	}
}
