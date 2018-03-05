package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetPenSizeCommand implements Command {
	private double penSize;
	private Turtle myTurtle;
	public SetPenSizeCommand(double width, Turtle turtle) {
		penSize = width;
		myTurtle = turtle;
	}
	@Override
	public double execute() {
		myTurtle.getPen().setWidth(penSize);
		return penSize;
	}

}
