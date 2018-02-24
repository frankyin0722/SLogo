package command.Control;

import java.util.List;
import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;

public class DoTimesCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<CommandNode> myParameters;
	private List<CommandNode> mySubCommands;
	
	public DoTimesCommand(CommandNode parameterParent, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myParameters = parameterParent.getNodeChildren();
		mySubCommands = subCommandsParent.getNodeChildren();
	}

	@Override
	public double execute() {
		myInterpreter.getVariables().setVariable((double) 0.0, myParameters.get(0).getCommandName());
		Variable var = myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName());
		for (CommandNode parameter : myParameters) {
			myInterpreter.interpretTree(parameter);
		}
		int end = (int) myParameters.get(1).getNodeValue();
		for (int i = 0; i < end; i++) {
			var.setValue(i+1); 
			for (int j = 0; j < mySubCommands.size(); j++) {
				myInterpreter.interpretTree(mySubCommands.get(j));
			}
		}
		return (double) var.getValue();
	}
}
