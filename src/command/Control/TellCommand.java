package command.Control;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import turtle.TurtleController;
/**
 * sets turtles that will follow commands hereafter 
 */
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
	
	/**
	 * sets turtles that will follow commands hereafter and returns last value in turtles list 
	 * note: if turtle has not previously existed, it is created and placed at the home location; if more than one turtle is active, commands run return value associated with the last active turtle 
	 */
	@Override
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
