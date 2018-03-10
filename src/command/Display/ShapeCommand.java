package command.Display;

import command.Command;
import turtle.TurtleController;

public class ShapeCommand implements Command {
	private TurtleController myController;
	public ShapeCommand(TurtleController controller) {
		myController = controller;
	}
	@Override
	public double execute() {
		return myController.getShapeIndex();
	}

}
