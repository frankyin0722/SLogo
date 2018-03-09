package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;

public class IDCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public IDCommand(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtleIndex();
	}
}
