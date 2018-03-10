package command.Display;

import command.Command;
import turtle.TurtleController;
/**
 * changes the shape of a turtle
 */
public class SetShapeCommand implements Command {
	private TurtleController myController;
	private int myIndex;
	public SetShapeCommand(double index, TurtleController controller) {
		myController = controller;
		myIndex = (int)index;
	}
	/**
	 * sets the shape of the turtle to that of the index specified
	 */
	@Override
	public double execute() {
		myController.setShape(myIndex);
		return myIndex;
	}

}
