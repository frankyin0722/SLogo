package command.Display;

import command.Command;
import turtle.TurtleController;

/**
 * specified in resources, not given instructions for how to implement. Exists if implementation is ever needed
 */
public class ClearStampsCommand implements Command {
	private TurtleController myController;
	public ClearStampsCommand(TurtleController controller) {
		myController = controller;
	}
	@Override
	public double execute() {
		return myController.clearStamps();
	}

}
