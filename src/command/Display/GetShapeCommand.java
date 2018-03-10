package command.Display;

import command.Command;
import turtle.TurtleController;

public class GetShapeCommand implements Command {
	private TurtleController myController;
	public GetShapeCommand(TurtleController controller) {
		myController = controller;
	}
	@Override
	public double execute() {
		return myController.getShapeIndex();
	}

}
