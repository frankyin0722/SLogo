package command.Math;

import command.Command;
/**
 * takes the product of two parameters
 */
public class ProductCommand implements Command {
	private double input1;
	private double input2;
	
	public ProductCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	/**
	 * returns the product
	 */
	public double execute(){
		return (input1*input2);
	}
}
