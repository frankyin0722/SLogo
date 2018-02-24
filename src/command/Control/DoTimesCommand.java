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
		//System.out.println("!!!");
		myInterpreter.getVariables().setVariable((double) 0.0, myParameters.get(0).getCommandName());
		//System.out.println("!!!");
		Variable var = myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName());
		System.out.println(myInterpreter.getVariables().checkVariable(myParameters.get(0).getCommandName()));
		System.out.println(myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName()).getValue());
		//System.out.println(myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName()));
		for (CommandNode parameter : myParameters) {
			myInterpreter.interpretTree(parameter);
		}
		int end = (int) myParameters.get(1).getNodeValue();
		System.out.println(end);
		for (int i = 0; i < end; i++) {
			//System.out.println(myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName()).getValue());
			double newvalue = (double) i + 1;
			var.setValue(newvalue); 
			System.out.println(myInterpreter.getVariables().getVariable(myParameters.get(0).getCommandName()).getValue());
			for (int j = 0; j < mySubCommands.size(); j++) {
				myInterpreter.interpretTree(mySubCommands.get(j));
			}
		}
		return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
	}
}
