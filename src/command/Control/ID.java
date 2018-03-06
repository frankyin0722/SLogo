package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;

public class ID implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public ID(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtleIndex();
	}
}
