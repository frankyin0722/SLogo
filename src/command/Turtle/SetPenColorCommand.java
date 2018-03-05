package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetPenColorCommand implements Command{
	private Turtle myTurtle;
	private int colorIndex;
	public SetPenColorCommand(double index, Turtle turtle) {
		myTurtle = turtle;
		colorIndex = (int)index;
	}
	@Override
	public double execute() {
		myTurtle.setColorByIndex(colorIndex);
		return colorIndex;
	}

}
