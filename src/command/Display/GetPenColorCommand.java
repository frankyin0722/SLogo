package command.Display;

import command.Command;
import turtle.TurtleController;
/**
 * gets the index of the pen color of a turtle 
 */
public class GetPenColorCommand implements Command {
	private TurtleController myController;
	public GetPenColorCommand(TurtleController controller) {
		myController = controller;
	}
	/**
	 * returns the index
	 */
	@Override
	public double execute() {
		return myController.getColorIndex();
	}

}
