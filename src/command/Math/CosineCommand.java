package command.Math;

import command.Command;
/**
 * takes the cosine of one parameter
 */
public class CosineCommand implements Command{
	private double input1;
	public CosineCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	/**
	 * returns the cosine
	 */
	public double execute(){
		return Math.cos(input1);
	}
}
