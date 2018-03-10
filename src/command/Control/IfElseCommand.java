package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
/**
 * run the commands given in the first list if the condition is evaluated to be true, otherwise run the commands given in the second list
 */
public class IfElseCommand implements Command {
	private CommandTreeInterpreter myInterpreter;
	private boolean myExpression;
	private List<CommandNode> mySubCommandsTrue;
	private List<CommandNode> mySubCommandsFalse;
	
	public IfElseCommand (CommandNode expression, CommandNode subCommandsParentTrue, CommandNode subCommandsParentFalse, CommandTreeInterpreter tree) {
		if (expression.getNodeValue() == 0) {
			myExpression = false;
		}
		else {
			myExpression = true;
		}
		mySubCommandsTrue = subCommandsParentTrue.getNodeChildren();
		mySubCommandsFalse = subCommandsParentFalse.getNodeChildren();
		myInterpreter = tree;
	}
	
	/**
	 * runs the commands by order given in the first list if the condition is evaluated to be true, otherwise runs the commands given in the second list 
	 */
	@Override
	public double execute() {
		if (myExpression) {
			for (int i = 0; i < mySubCommandsTrue.size(); i++) {
				myInterpreter.interpretTree(mySubCommandsTrue.get(i));
			}
			return returnValue(mySubCommandsTrue);
		}
		else {
			
			for (int i = 0; i < mySubCommandsFalse.size(); i++) {
				myInterpreter.interpretTree(mySubCommandsFalse.get(i));
			}
			return returnValue(mySubCommandsFalse);
		}
	}
	
	private double returnValue(List<CommandNode> mySelectedSubCommands) {
		if (mySelectedSubCommands.size() != 0) {
			return (double) mySelectedSubCommands.get(mySelectedSubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
	
}
