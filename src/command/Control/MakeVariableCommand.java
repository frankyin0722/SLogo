package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;
/**
 * assign the value of input to variable, creating the variable if necessary
 */
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
	
	/**
	 * assigns the value of input to variable, and creates the variable if necessary 
	 */
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
	}
	
}
