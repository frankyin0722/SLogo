package command.Turtle;

import turtle.Turtle;

public class XCoordinateCommand {
	private Turtle myTurtle;
	public XCoordinateCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getX();
	}
}
