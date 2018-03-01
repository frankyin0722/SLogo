package command.Math;

import command.Command;
/**
 * divides the first parameter by the second
 */
public class QuotientCommand implements Command {
	private double input1;
	private double input2;
	
	public QuotientCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	/**
	 * returns the quotient
	 */
	public double execute(){
		return (input1/input2);
	}
}
