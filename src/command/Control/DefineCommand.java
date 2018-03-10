package command.Control;

import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
/**
 * define a command that might be used later on 
 */
public class DefineCommand implements Command{
	private String myCommandName;
	private List<CommandNode> myVariables;
	private CommandTreeInterpreter myInterpreter;
	private boolean successfullyCreated;
	
	public DefineCommand(CommandNode commandName, CommandNode variables, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariables = variables.getNodeChildren();
		myCommandName = commandName.getCommandName();
		successfullyCreated = (commandName.getNodeValue()==1 ? true : false);
	}
	
	/**
	 * once successfully defined, maps the parameters of this command to the user-define command name
	 */
	public double execute() {
		if (!successfullyCreated) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError2")), "CommandMessageError3");
			return 0;
		}
		if (myInterpreter.getUserCommandParameters().containsKey(myCommandName)) {
			myInterpreter.getUserCommandParameters().replace(myCommandName, myVariables);
		}
		else {
			myInterpreter.getUserCommandParameters().put(myCommandName, myVariables);
		}
		if (myInterpreter.getUserCommands().containsKey(myCommandName)) {
			myInterpreter.getUserCommands().remove(myCommandName); // remove the old user-defined command if it exists
		}
		return 1.0;
	}
}
