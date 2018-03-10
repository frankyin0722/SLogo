package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
/**
 * get the id number of the current active turtle 
 */
public class IDCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public IDCommand(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	/**
	 * returns the id number of the current active turtle 
	 */
	public double execute() {
		return myInterpreter.getCurrentActiveTurtleIndex();
	}
}
