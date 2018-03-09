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
			//System.out.println("i am here");
			for (int i = 0; i < mySubCommands.size(); i++) {
				myInterpreter.interpretTree(mySubCommands.get(i));
				//System.out.println("i am here2");
			}
		}
		if (mySubCommands.size() != 0) {
			//System.out.println(mySubCommands.get(mySubCommands.size()-1).getNodeValue());
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
}
