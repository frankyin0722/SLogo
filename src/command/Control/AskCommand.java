package command.Control;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class AskCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<Integer> myTemporaryActiveTurtleIndices;
	private List<CommandNode> mySubCommands;
	private List<Integer> myOldActiveTurtleIndices;
	
	public AskCommand(CommandNode temporaryactiveparent, CommandNode subcommandsParent, CommandTreeInterpreter tree) {
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
		myInterpreter.getTurtleController().resetActiveTurtles(myTemporaryActiveTurtleIndices);
		System.out.println("i got here");
		System.out.println(myInterpreter.getCurrentActiveTurtleIndices());
		for (int i = 0; i < mySubCommands.size(); i++) {
			myInterpreter.interpretTree(mySubCommands.get(i));
		}
		System.out.println("i got here2");
		myInterpreter.getTurtleController().resetActiveTurtles(myOldActiveTurtleIndices);
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
}
