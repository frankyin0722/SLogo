package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class IsPenDownCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public IsPenDownCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().getTurtle(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	public double execute(){
		return myTurtle.getPen().PenUp() ? 0: 1;
	}
}
