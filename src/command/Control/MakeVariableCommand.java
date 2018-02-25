package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;

public class MakeVariableCommand implements Command {
	private CommandTreeInterpreter myInterpreter;
	private CommandNode myVariable;
	private double myValue;
	
	public MakeVariableCommand(CommandNode assignedvalue, CommandNode variable, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariable = variable;
		myValue = assignedvalue.getNodeValue();
	}
	
	@Override
	public double execute() {
		variableDeclare(myVariable);
		return myVariable.getNodeValue();
	}
	
	private void variableDeclare(CommandNode variable) {
		if (myInterpreter.getVariables().checkVariable(variable.getCommandName())) { // variable already exist
			myInterpreter.getVariables().setVariable(myValue, variable.getCommandName());
		}
		else {
			Variable currentVariable = new Variable(myValue);
			myInterpreter.getVariables().addVariable(currentVariable, variable.getCommandName());
		}
		variable.setNodeValue(myValue);
		System.out.println(variable.getCommandName());
		System.out.println(myInterpreter.getVariables().checkVariable(variable.getCommandName()));
		System.out.println(myInterpreter.getVariables().getVariable(variable.getCommandName()).getValue());
	}
	
}
