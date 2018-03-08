package command.Turtle;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class ShowTurtleCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public ShowTurtleCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	public double execute(){
		myTurtle.setVisibility(true);
		return 1;
	}
	
}
