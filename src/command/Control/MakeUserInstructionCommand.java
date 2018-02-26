package command.Control;

import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class MakeUserInstructionCommand implements Command {
	private String myCommandName;
	private List<CommandNode> myVariables;
	private List<CommandNode> mySubCommands;
	private CommandTreeInterpreter myInterpreter;
	
	public MakeUserInstructionCommand (CommandNode commandName, CommandNode variables, CommandNode subCommands, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariables = variables.getNodeChildren();
		mySubCommands = subCommands.getNodeChildren();
		myCommandName = commandName.getCommandName();
	}
	
	@Override
	public double execute() {
		if (myInterpreter.getVariables().checkVariable(myCommandName)) {
			System.out.println("Failed to create user-defined method: method name already exists in variables");
			return 0.0; // if the commandName is already a variable, fails 
		}
		
		CommandNode methodRoot = new CommandNode("UserDefined", myCommandName, null, 0);
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
		
		for (CommandNode variable : myVariables) {
			if (myInterpreter.getUserCommands().containsKey(variable.getCommandName())) {
				myInterpreter.getUserCommands().remove(myCommandName);
				myInterpreter.getUserCommandParameters().remove(myCommandName);
				System.out.println("Failed to create user-defined method: parameters are user-defined methods");
				return 0.0; // if the variable used is already a user command, fails 
			}
		}
		
		
		return 1.0;
	}
	
}
