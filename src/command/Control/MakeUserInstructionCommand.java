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
		if (myInterpreter.getUserCommands().containsKey(myCommandName)) {
			return 1.0;
		}
		else {
			return 0.0;
		}
	}
	
}
