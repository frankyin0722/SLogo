package command.Control;

import interpreter.CommandTreeInterpreter;

public class ID {
	private CommandTreeInterpreter myInterpreter;
	
	public ID(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtleIndex();
	}
}
