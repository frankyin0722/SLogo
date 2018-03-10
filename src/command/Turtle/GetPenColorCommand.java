package command.Turtle;

import command.Command;
import turtle.Turtle;

public class PenColorCommand implements Command {
	private Turtle myTurtle;
	public PenColorCommand(Turtle turtle) {
		myTurtle = turtle;
	}
	@Override
	public double execute() {
		return myTurtle.getColorIndex();
	}

}
