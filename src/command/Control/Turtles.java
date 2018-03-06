package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;

public class Turtles implements Command{
	private CommandTreeInterpreter myInterpreter;
	
	public Turtles(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtles().size();
	}
}
