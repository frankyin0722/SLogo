package command.Control;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import turtle.TurtleController;

public class TellCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<CommandNode> myActiveTurtles;
	
	public TellCommand(CommandNode parentNode, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myActiveTurtles = new ArrayList<>();
		for (int i = 0; i < parentNode.getNodeChildren().size(); i++) {
			myActiveTurtles.add(parentNode.getNodeChildren().get(i));
		}
	}
	
	public double execute() {
		int maxIndex = 0; 
		TurtleController myTurtleController = myInterpreter.getTurtleController();
		List<Integer> newCurrentActiveTurtles = new ArrayList<>();
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			if (myActiveTurtles.get(i).getNodeValue() >= maxIndex) {
				maxIndex = (int) myActiveTurtles.get(i).getNodeValue();
			}
		}
		
		myTurtleController.makeNewTurtles(maxIndex - myTurtleController.size());
		for (CommandNode active : myActiveTurtles) {
			newCurrentActiveTurtles.add((int) active.getNodeValue());
		}
		
		myTurtleController.resetActiveTurtles(newCurrentActiveTurtles);
		
		return myActiveTurtles.get(myActiveTurtles.size()-1).getNodeValue();
	} 
}
