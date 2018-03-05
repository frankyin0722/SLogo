package command.Math;

import command.Command;
/**
 * takes the natural logarithm of one parameter
 */
public class NaturalLogCommand implements Command{
	private double input1;
	
	public NaturalLogCommand (double expr1){
		input1 = expr1;
	}
	/**
	 * returns the natural log
	 */
	public double execute(){
		return Math.log(input1);
	}
}
