package command.Boolean;

import command.Command;
/**
 * checks the parameter against 0
 */
public class NotCommand implements Command {
	private double input1;
	
	public NotCommand (double test1){
		input1 = test1;
	}
	/**
	 * returns 1 if 0, else 0
	 */
	public double execute(){
		return (input1==0) ? 1:0;
	}
	
}
