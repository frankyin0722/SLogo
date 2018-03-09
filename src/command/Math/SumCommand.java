package command.Math;

import command.Command;
/**
 * takes the sum of two parameters
 */
public class SumCommand implements Command{
	private double input1;
	private double input2;
	
	public SumCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	/**
	 * returns the sum
	 */
	public double execute(){
		System.out.println("sum of " + input1 + " " + input2 + "=" + (input1+input2));
		return input1+input2;
	}
	
}
