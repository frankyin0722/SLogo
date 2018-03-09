package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class IsShowingCommand implements Command {
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public IsShowingCommand (CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	public double execute(){
		return myTurtle.isVisible()?1:0;
	}
}
