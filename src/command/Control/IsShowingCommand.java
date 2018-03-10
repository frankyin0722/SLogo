package command.Control;

import command.Command;
import interpreter.CommandTreeInterpreter;
import turtle.Turtle;
/**
 * get the showing status of the current active turtle 
 */
public class IsShowingCommand implements Command {
	private Turtle myTurtle;
	private CommandTreeInterpreter myInterpreter;
	
	public IsShowingCommand (CommandTreeInterpreter tree){
		myInterpreter = tree;
	}
	
	/**
	 * returns 0 if the current active turtle is not showing or if the current active turtle is not available, and returns 1 otherwise 
	 */
	@Override
	public double execute(){
		if (myInterpreter.getCurrentActiveTurtleIndex()-1<0 || myInterpreter.getCurrentActiveTurtleIndex()-1>myInterpreter.getCurrentAvailableTurtles().size()){
			return 0;
		}
		myTurtle = myInterpreter.getCurrentAvailableTurtles().get(myInterpreter.getCurrentActiveTurtleIndex()-1);
		return myTurtle.checkVisibility()?1:0;
	}
}
