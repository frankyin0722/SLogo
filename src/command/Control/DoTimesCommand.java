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
		
		int end = (int) myParameters.get(1).getNodeValue();
		
		for (int i = 0; i < end; i++) { 
			double newvalue = (double) var.getValue() + 1;
			var.setValue(newvalue); 
			//System.out.println(myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName()).getValue());
			for (int j = 0; j < mySubCommands.size(); j++) {
				myInterpreter.interpretTree(mySubCommands.get(j));
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
