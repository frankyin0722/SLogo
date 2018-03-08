package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;

public class TurtlesCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public TurtlesCommand(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtles().size();
	}
}
