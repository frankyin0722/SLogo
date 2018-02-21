package command.Turtle;

import command.Command;
import turtle.Turtle;

public class ShowTurtleCommand implements Command{
	private Turtle myTurtle;
	
	public ShowTurtleCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		myTurtle.setVisible(true);
		return 1;
	}
	
}
