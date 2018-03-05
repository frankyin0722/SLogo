package command.Math;

import command.Command;
/**
 * takes the sum of two parameters
 */
public class SumCommand implements Command{
	private double[] inputs;
	
	public SumCommand (double... exprs){
		inputs = exprs;
	}
	/**
	 * returns the sum
	 */
	public double execute(){
		int result = 0;
		for(double input:inputs) {
			result+=input;
		}
		return result;
	}
	
}
