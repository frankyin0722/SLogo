package command.Boolean;

import command.Command;
/**
 * checks if the first parameter is greater than the second
 */
public class GreaterThanCommand implements Command{
	private double input1;
	private double input2;
	
	public GreaterThanCommand (double test1, double test2){
		input1 = test1;
		input2 = test2;
	}
	/**
	 * returns 1 if first is greater than second, else 0
	 */
	public double execute(){
		return (input1 > input2) ? 1:0;
	}
	
}
