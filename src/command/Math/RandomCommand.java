package command.Math;

import command.Command;

/**
 * generates a random number from 0 to the parameter
 */
public class RandomCommand implements Command {
private double input1;
	
	public RandomCommand (double expr1){
		input1 = expr1;
	}
	/**
	 * returns the random number
	 */
	public double execute(){
		return (Math.random() * input1);
	}
}
