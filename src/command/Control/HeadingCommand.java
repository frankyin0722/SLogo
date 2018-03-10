package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;
/**
 * get the heading direction of the current active turtle 
 */
public class HeadingCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public HeadingCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
	}
	
	/**
	 * returns the heading direction of the current active turtle 
	 */
	public double execute(){
		return Math.toDegrees(myTurtle.getDirection());
	}
}
