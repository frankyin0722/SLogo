package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;
/**
 * get the turtle's X coordinate from the center of the screen
 */
public class XCoordinateCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public XCoordinateCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
	}
	
	/**
	 * returns the turtle's X coordinate from the center of the screen 
	 */
	@Override
	public double execute(){
		if (myInterpreter.getCurrentActiveTurtleIndex()-1<0 && myInterpreter.getCurrentActiveTurtleIndex()-1>myInterpreter.getCurrentAvailableTurtles().size()){
			return 0;
		}
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
		return myTurtle.getX();
	}
}
