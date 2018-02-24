package command.Turtle;

import command.Command;
import turtle.Turtle;

public class YCoordinateCommand implements Command {
	private Turtle myTurtle;
	public YCoordinateCommand (Turtle turtle){
		myTurtle = turtle;
	}
	public double execute(){
		return myTurtle.getY();
	}
}
