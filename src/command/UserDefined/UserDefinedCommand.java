package command.UserDefined;

import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;

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
		System.out.println("user defined command value: " + storedMethod.getNodeValue());
		return storedMethod.getNodeValue();	
	}

}
