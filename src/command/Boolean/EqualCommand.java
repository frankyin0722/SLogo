package command.Boolean;

import command.Command;
/**
 * checks if the two parameters are equal
 */
public class EqualCommand implements Command {
	private double input1;
	private double input2;
	
	public EqualCommand (double test1, double test2){
		input1 = test1;
		input2 = test2;
	}
	/**
	 * returns 1 if equal, else 0
	 */
	public double execute(){
		return (input1 == input2) ? 1:0;
	}
	
}
