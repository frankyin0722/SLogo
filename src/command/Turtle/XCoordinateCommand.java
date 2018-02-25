package command.Turtle;

import command.Command;
import turtle.Turtle;

public class XCoordinateCommand implements Command{
	private Turtle myTurtle;
	public XCoordinateCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getX();
	}
}
