package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class YCoordinateCommand implements Command {
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public YCoordinateCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
	}
	
	public double execute(){
		if (myInterpreter.getCurrentActiveTurtleIndex()-1<0 && myInterpreter.getCurrentActiveTurtleIndex()-1>myInterpreter.getCurrentAvailableTurtles().size()){
			return 0;
		}
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
		return -1*myTurtle.getY();
	}
}
