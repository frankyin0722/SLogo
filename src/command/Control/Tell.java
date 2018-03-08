package command.Control;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import turtle.TurtleController;

public class Tell implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<CommandNode> myActiveTurtles;
	
	public Tell(CommandNode parentNode, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myActiveTurtles = new ArrayList<>();
		for (int i = 0; i < parentNode.getNodeChildren().size(); i++) {
			myActiveTurtles.add(parentNode.getNodeChildren().get(i));
		}
	}
	
	public double execute() {
		int maxIndex = 0; 
		TurtleController currentAvailableTurtles = myInterpreter.getCurrentAvailableTurtles();
		List<Integer> newCurrentActiveTurtles = new ArrayList<>();
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			if (myActiveTurtles.get(i).getNodeValue() >= maxIndex) {
				maxIndex = (int) myActiveTurtles.get(i).getNodeValue();
			}
		}
		currentAvailableTurtles.makeNewTurtles(maxIndex - currentAvailableTurtles.size() + 1);
		for (int i = currentAvailableTurtles.size(); i < maxIndex; i++) {
			//Turtle newturtle = new Turtle();
			//currentAvailableTurtles.add(newturtle);
		}
		for (CommandNode active : myActiveTurtles) {
			newCurrentActiveTurtles.add((int) active.getNodeValue());
		}
		myInterpreter.setCurrentActiveTurtleIndices(newCurrentActiveTurtles);
		return myActiveTurtles.get(myActiveTurtles.size()-1).getNodeValue();
	} 
}
