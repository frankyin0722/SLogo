package command.Turtle;

import turtle.Turtle;

public class YCoordinateCommand {
	private Turtle myTurtle;
	public YCoordinateCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getY();
	}
}
