package command.Turtle;

import command.Command;
import turtle.Turtle;
/**
 * sets the size of a pen of a turtle
 */
public class SetPenSizeCommand implements Command {
	private double penSize;
	private Turtle myTurtle;
	public SetPenSizeCommand(double width, Turtle turtle) {
		penSize = width;
		myTurtle = turtle;
	}
	/**
	 * sets the pen size
	 */
	@Override
	public double execute() {
		myTurtle.getPen().setWidth(penSize);
		return penSize;
	}

}
