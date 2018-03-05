package command.Math;

import command.Command;
/**
 * takes the additive inverse of one parameter
 */
public class MinusCommand implements Command{
	private double input1;
	
	public MinusCommand (double expr1){
		input1 = expr1;
	}
	/**
	 * returns the additive inverse
	 */
	public double execute(){
		return (-1* input1);
	}
}
