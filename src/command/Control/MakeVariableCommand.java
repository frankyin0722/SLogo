package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;

public class MakeVariableCommand implements Command {
	private CommandTreeInterpreter myInterpreter;
	private CommandNode myVariable;
	private double myValue;
	
	public MakeVariableCommand(CommandNode variable, CommandNode assignedvalue, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariable = variable;
		myInterpreter.interpretTree(assignedvalue);
		myValue = assignedvalue.getNodeValue();
	}
	
	@Override
	public double execute() {
		variableDeclare(myVariable);
		if(myVariable.getCommandName().equals(":x"))
			System.out.println("HIHI" + myVariable.getNodeValue() + myVariable.getCommandName());
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
	}
	
}
