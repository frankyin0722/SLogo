package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
/**
 * get the number of available turtles so far 
 */
public class TurtlesCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public TurtlesCommand(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	/**
	 * returns number of turtles created so far 
	 */
	@Override
	public double execute() {
		return myInterpreter.getCurrentActiveTurtles().size();
	}
}
