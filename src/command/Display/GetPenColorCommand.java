package command.Display;

import command.Command;
import turtle.TurtleController;

public class GetPenColorCommand implements Command {
	private TurtleController myController;
	public GetPenColorCommand(TurtleController controller) {
		myController = controller;
	}
	@Override
	public double execute() {
		return myController.getColorIndex();
	}

}
