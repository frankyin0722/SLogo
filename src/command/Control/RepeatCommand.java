package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import variables.Variable;
//purely speculative, will likely change a lot, but possible model for control commands
public class RepeatCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private int myNumTimes;
	private List<CommandNode> mySubCommands;
	private static String repcount = ":repcount";

	public RepeatCommand(CommandNode numTimes, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myNumTimes = (int) numTimes.getNodeValue();
		mySubCommands = subCommandsParent.getNodeChildren();
	}

	@Override
	public double execute() {
		for (int i = 0; i < myNumTimes; i++) {
			repcountUpdate((double) i+1);
			for(CommandNode subCommand: mySubCommands) {
				System.out.println("i was hereeee");
				myInterpreter.interpretTree(subCommand);
			}
		}
		if (mySubCommands.size() != 0) {
			System.out.println(mySubCommands.get(mySubCommands.size()-1).getNodeValue());
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
	
	private void repcountUpdate(double count) {
		if (myInterpreter.getVariables().checkVariable(repcount)) {
			myInterpreter.getVariables().setVariable(count, repcount);
		}
		else {
			Variable newvar = new Variable((double) 1);
			myInterpreter.getVariables().addVariable(newvar, repcount);
		}
	}
	
}
