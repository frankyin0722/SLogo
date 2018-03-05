package command.Control;

import java.util.ArrayList;
import java.util.List;

import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class Ask {
	private CommandTreeInterpreter myInterpreter;
	private List<Integer> myTemporaryActiveTurtleIndices;
	private List<CommandNode> mySubCommands;
	private List<Integer> myOldActiveTurtleIndices;
	
	public Ask(CommandNode temporaryactiveparent, CommandNode subcommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myTemporaryActiveTurtleIndices = new ArrayList<>();
		mySubCommands = subcommandsParent.getNodeChildren();
		myOldActiveTurtleIndices = myInterpreter.getCurrentActiveTurtleIndices();
		for (int i = 0; i < temporaryactiveparent.getNodeChildren().size(); i++) {
			int turtleindex = (int) temporaryactiveparent.getNodeChildren().get(i).getNodeValue();
			if (turtleindex>=1 && turtleindex<=myInterpreter.getCurrentAvailableTurtles().size()) {
				myTemporaryActiveTurtleIndices.add((int) temporaryactiveparent.getNodeChildren().get(i).getNodeValue());
			}
		}
	}
	
	public double execute() {
		myInterpreter.setCurrentActiveTurtleIndices(myTemporaryActiveTurtleIndices);
		for (int i = 0; i < mySubCommands.size(); i++) {
			myInterpreter.interpretTree(mySubCommands.get(i));
		}
		myInterpreter.setCurrentActiveTurtleIndices(myOldActiveTurtleIndices);
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
}
