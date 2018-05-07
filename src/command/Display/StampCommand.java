package command.Display;

import command.Command;
import turtle.TurtleController;

/**
 * specified in resources, not given instructions for how to implement. Exists if implementation is ever needed
 */
public class StampCommand implements Command {
	private TurtleController myController;
	public StampCommand(TurtleController controller) {
		myController = controller;

	}
	@Override
	public double execute() {
		return myController.setStamp();
	}

}
