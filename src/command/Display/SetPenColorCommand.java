package command.Display;

import command.Command;
import turtle.TurtleController;

public class SetPenColorCommand implements Command{
	private TurtleController myController;
	private int colorIndex;
	public SetPenColorCommand(double index, TurtleController controller) {
		myController = controller;
		colorIndex = (int)index;
	}
	@Override
	public double execute() {
		myController.setColorByIndex(colorIndex);
		return colorIndex;
	}

}
