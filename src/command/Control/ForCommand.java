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
	private static int START_INDEX = 1;
	private static int END_INDEX = 2;
	private static int INCREMENT_INDEX = 3;
	
	public ForCommand(CommandNode parameterParent, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myParameters = parameterParent.getNodeChildren();
		mySubCommands = subCommandsParent.getNodeChildren();
	}
	@Override
	public double execute() {
		myInterpreter.getVariables().setVariable((double) myParameters.get(1).getNodeValue(), myParameters.get(0).getCommandName());
		
		Variable var = myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName());
		
		int start = (int) myParameters.get(START_INDEX).getNodeValue();
		int end = (int) myParameters.get(END_INDEX).getNodeValue();
		int increment = (int) myParameters.get(INCREMENT_INDEX).getNodeValue();
		
		for (int i = start; i <= end; i = i + increment) {
			for (int j = 0; j < mySubCommands.size(); j++) {
				myInterpreter.interpretTree(mySubCommands.get(j));
			}
			double newvalue = (double) i + increment;
			var.setValue(newvalue); 
		}
		var.setValue((double) var.getValue()-increment);
		
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
	
}
