package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class IfCommand implements Command {
	private CommandTreeInterpreter myInterpreter;
	private List<CommandNode> myParameters;
	private List<CommandNode> mySubCommands;
	
	public IfCommand (CommandNode parameterParent, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myParameters = parameterParent.getNodeChildren();
		mySubCommands = subCommandsParent.getNodeChildren();
		myInterpreter = tree;
	}
	
	@Override
	public double execute() {
		// TODO Auto-generated method stub
		int expression = (int) myParameters.get(0).getNodeValue();
		System.out.println(expression);
		if (expression != 0) {
			for (int i = 0; i < mySubCommands.size(); i++) {
				myInterpreter.interpretTree(mySubCommands.get(i));
			}
		}
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
}
