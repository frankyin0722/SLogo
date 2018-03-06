package command.Turtle;

import command.Command;
import turtle.Turtle;

public class ShapeCommand implements Command {
	private Turtle myTurtle;
	public ShapeCommand(Turtle turtle) {
		myTurtle = turtle;
	}
	@Override
	public double execute() {
		return myTurtle.getShapeIndex();
	}

}
