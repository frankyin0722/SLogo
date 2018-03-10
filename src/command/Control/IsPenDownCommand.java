package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;
/**
 * get the status of the pen of the current active turtle 
 */
public class IsPenDownCommand implements Command{
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public IsPenDownCommand(CommandTreeInterpreter tree){
		myInterpreter = tree;
	}
	
	/**
	 * returns 1 if the pen of the current active turtle is down, and returns 0 if the pen of the current active turtle is up 
	 */
	@Override
	public double execute(){
		if (myInterpreter.getCurrentActiveTurtleIndex()-1<0 && myInterpreter.getCurrentActiveTurtleIndex()-1>myInterpreter.getCurrentAvailableTurtles().size()){
			return 0;
		}
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
		return myTurtle.getPen().PenUp() ? 0: 1;
	}
}
