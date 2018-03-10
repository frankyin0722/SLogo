package command.Display;

import command.Command;
import turtle.TurtleController;

public class SetBackgroundCommand implements Command {
	private TurtleController myController;
	private int colorIndex;
	public SetBackgroundCommand(double index, TurtleController controller) {
		myController = controller;
		colorIndex = (int)index;
	}
	@Override 
	public double execute() {
		myController.setBackground(colorIndex);
		return colorIndex;
	}
}
