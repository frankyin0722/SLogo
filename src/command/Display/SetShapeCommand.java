package command.Display;

import command.Command;
import turtle.TurtleController;

public class SetShapeCommand implements Command {
	private TurtleController myController;
	private int myIndex;
	public SetShapeCommand(double index, TurtleController controller) {
		myController = controller;
		myIndex = (int)index;
	}
	@Override
	public double execute() {
		myController.setShape(myIndex);
		return myIndex;
	}

}
