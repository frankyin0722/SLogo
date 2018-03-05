package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class HeadingCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public HeadingCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	public double execute(){
		return myTurtle.getDirection();
	}
}
