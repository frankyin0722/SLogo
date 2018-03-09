package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class YCoordinateCommand implements Command {
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public YCoordinateCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().getTurtle(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	public double execute(){
		return -1*myTurtle.getY();
	}
}
