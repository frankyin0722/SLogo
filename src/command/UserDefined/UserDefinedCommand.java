package command.UserDefined;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class UserDefinedCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private String myDefinedCommandName;
	
	public UserDefinedCommand(String myDefinedName, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myDefinedCommandName = myDefinedName;
	}
	
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
