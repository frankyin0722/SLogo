package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetShapeCommand implements Command{
	private int shapeIndex;
	private Turtle myTurtle;
	public SetShapeCommand(double value, Turtle turtle) {
		shapeIndex = (int) value;
		myTurtle = turtle;
	}
	@Override
	public double execute() {
		myTurtle.setShape(shapeIndex);
		return shapeIndex;
	}

}
