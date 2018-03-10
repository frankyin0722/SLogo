package command.Control;

import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
/**
 * assigns command(s) given in the second list to the command name using parameters given in first list as variables; when the command is used later in a program, any given values are assigned to variables that can be accessed when the command list is run
 */
public class MakeUserInstructionCommand implements Command {
	private String myCommandName;
	private List<CommandNode> myVariables;
	private List<CommandNode> mySubCommands;
	private CommandTreeInterpreter myInterpreter;
	private boolean successfullyCreated;
	private static String BracketType = "Bracket";
	
	public MakeUserInstructionCommand (CommandNode commandName, CommandNode variables, CommandNode subCommands, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariables = variables.getNodeChildren();
		mySubCommands = subCommands.getNodeChildren();
		myCommandName = commandName.getCommandName();
		successfullyCreated = (commandName.getNodeValue()==1 ? true : false);
	}
	
	/**
	 * returns 1 if command is successfully defined, otherwise 0; maps the parameters and the sub-commands to the command name correspondingly 
	 */
	@Override
	public double execute() {
		if (!successfullyCreated) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError2")), "CommandMessageError3");
			return 0;
		}
		CommandNode methodRoot = new CommandNode(BracketType, myCommandName, null, 0);
		for (CommandNode subcommand : mySubCommands) {
			methodRoot.addChild(subcommand);
		}
		if (myInterpreter.getUserCommands().containsKey(myCommandName)) {
			myInterpreter.getUserCommands().replace(myCommandName, methodRoot);
		}
		else {
			myInterpreter.getUserCommands().put(myCommandName, methodRoot);
		}
		if (myInterpreter.getUserCommandParameters().containsKey(myCommandName)) {
			myInterpreter.getUserCommandParameters().replace(myCommandName, myVariables);
		}
		else {
			myInterpreter.getUserCommandParameters().put(myCommandName, myVariables);
		}
		return 1.0;
	}
	
}
