package command.Control;

import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class MakeUserInstructionCommand implements Command {
	private String myCommandName;
	private List<CommandNode> myVariables;
	private List<CommandNode> mySubCommands;
	private CommandTreeInterpreter myInterpreter;
	private boolean successfullyCreated;
	
	public MakeUserInstructionCommand (CommandNode commandName, CommandNode variables, CommandNode subCommands, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariables = variables.getNodeChildren();
		mySubCommands = subCommands.getNodeChildren();
		myCommandName = commandName.getCommandName();
		successfullyCreated = (commandName.getNodeValue()==1 ? true : false);
	}
	
	@Override
	public double execute() {
		if (!successfullyCreated) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError2")), "CommandMessageError3");
			return 0;
		}
		CommandNode methodRoot = new CommandNode("Bracket", myCommandName, null, 0);
		for (CommandNode subcommand : mySubCommands) {
			methodRoot.addChild(subcommand);
		}
		if (myInterpreter.getUserCommands().containsKey(myCommandName)) {
			myInterpreter.getUserCommands().replace(myCommandName, methodRoot);
			myInterpreter.getUserCommandParameters().replace(myCommandName, myVariables);
		}
		else {
			myInterpreter.getUserCommands().put(myCommandName, methodRoot);
			myInterpreter.getUserCommandParameters().put(myCommandName, myVariables);
		}
		return 1.0;
	}
	
}
