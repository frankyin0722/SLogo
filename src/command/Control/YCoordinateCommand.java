package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;
/**
 * get the turtle's Y coordinate from the center of the screen
 */
public class YCoordinateCommand implements Command {
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public YCoordinateCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
	}
	
	/**
	 * returns the turtle's Y coordinate from the center of the screen
	 */
	@Override
	public double execute(){
		if (myInterpreter.getCurrentActiveTurtleIndex()-1<0 && myInterpreter.getCurrentActiveTurtleIndex()-1>myInterpreter.getCurrentAvailableTurtles().size()){
			return 0;
		}
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
		return -1*myTurtle.getY();
	}
}
