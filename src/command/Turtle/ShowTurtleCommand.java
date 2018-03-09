package command.Turtle;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class ShowTurtleCommand implements Command{
	private Turtle myTurtle;
	
	public ShowTurtleCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		myTurtle.setVisibility(true);
		return 1;
	}
	
}
