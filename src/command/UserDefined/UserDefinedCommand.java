package command.UserDefined;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
/**
 * adapt to incorporate and execute any user-defined methods 
 */
public class UserDefinedCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private String myDefinedCommandName;
	
	public UserDefinedCommand(String myDefinedName, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myDefinedCommandName = myDefinedName;
	}
	
	/**
	 * gets the sub-commands from the user-define method map, and returns the value of the final command executed (or 0 if no commands are executed) 
	 */
	@Override
	public double execute() {
		CommandNode storedMethod = myInterpreter.getUserCommands().get(myDefinedCommandName);
		if (storedMethod==null) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError5");
			throw new CommandException(Resources.getString("CommandHeaderError"));
		}
		myInterpreter.interpretTree(storedMethod);
		
		return storedMethod.getNodeValue();	
	}

}
