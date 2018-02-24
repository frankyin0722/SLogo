package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
//purely speculative, will likely change a lot, but possible model for control commands
public class RepeatCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private int myNumTimes;
	private List<CommandNode> mySubCommands;

	public RepeatCommand(CommandNode numTimes, CommandNode subCommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myInterpreter.interpretTree(numTimes);
		myNumTimes = (int) numTimes.getNodeValue();
		mySubCommands = subCommandsParent.getNodeChildren();
	}

	@Override
	public double execute() {
		while(myNumTimes > 0) {
			for(CommandNode subCommand: mySubCommands) {
				myInterpreter.interpretTree(subCommand);
			}
			myNumTimes--;
		}
		double returnValue = mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		return returnValue;
	}
}
