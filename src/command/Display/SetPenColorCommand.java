package command.Display;

import command.Command;
import turtle.TurtleController;
/**
 * changes the pen color of a turtle
 */
public class SetPenColorCommand implements Command{
	private TurtleController myController;
	private int colorIndex;
	public SetPenColorCommand(double index, TurtleController controller) {
		myController = controller;
		colorIndex = (int)index;
	}
	/**
	 * sets the pen color of the turtle to that of the  index specified
	 */
	@Override
	public double execute() {
		myController.setColorByIndex(colorIndex);
		return colorIndex;
	}

}
