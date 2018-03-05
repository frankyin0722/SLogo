package command.Control;

import interpreter.CommandTreeInterpreter;

public class Turtles {
	private CommandTreeInterpreter myInterpreter;
	
	public Turtles(CommandTreeInterpreter tree) {
		myInterpreter = tree;
	}
	
	public double execute() {
		return myInterpreter.getCurrentActiveTurtles().size();
	}
}
