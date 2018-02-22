package command.Turtle;

import command.Command;
import turtle.Turtle;

public class HideTurtleCommand implements Command{
	private Turtle myTurtle;
	
	public HideTurtleCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		myTurtle.setVisibility(false);
		return 0;
	}
	
}
