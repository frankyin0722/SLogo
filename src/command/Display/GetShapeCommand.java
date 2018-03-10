package command.Display;

import command.Command;
import turtle.TurtleController;
/**
 * returns the shape of a turtle
 */
public class GetShapeCommand implements Command {
	private TurtleController myController;
	public GetShapeCommand(TurtleController controller) {
		myController = controller;
	}
	/**
	 * returns the shape
	 */
	@Override
	public double execute() {
		return myController.getShapeIndex();
	}

}
