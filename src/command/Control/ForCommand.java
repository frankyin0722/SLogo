package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;

public class ForCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<CommandNode> myParameters;
	private List<CommandNode> mySubCommands;
	
	public ForCommand(CommandNode parameterParent, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myParameters = parameterParent.getNodeChildren();
		mySubCommands = subCommandsParent.getNodeChildren();
	}
	@Override
	public double execute() {
		myInterpreter.getVariables().setVariable((double) myParameters.get(0).getNodeValue(), myParameters.get(0).getCommandName());
		Variable var = myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName());
		
		int start = (int) myParameters.get(1).getNodeValue();
		int end = (int) myParameters.get(2).getNodeValue();
		int increment = (int) myParameters.get(3).getNodeValue();
		
		for (int i = start; i < end; i = i + increment) {
			for (int j = 0; j < mySubCommands.size(); j++) {
				myInterpreter.interpretTree(mySubCommands.get(j));
			}
			double newvalue = (double) i + increment;
			var.setValue(newvalue); 
		}
		
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
	
}
