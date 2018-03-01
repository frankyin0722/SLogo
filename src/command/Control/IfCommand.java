package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class IfCommand implements Command {
	private CommandTreeInterpreter myInterpreter;
	private boolean myExpression;
	private List<CommandNode> mySubCommands;
	
	public IfCommand (CommandNode expression, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		if (expression.getNodeValue() != 0) {
			myExpression = true;
		}
		else {
			myExpression = false;
		}
		mySubCommands = subCommandsParent.getNodeChildren();
		myInterpreter = tree;
	}
	
	@Override
	public double execute() {
		
		if (myExpression) {
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
