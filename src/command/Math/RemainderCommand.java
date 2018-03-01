package command.Math;

import command.Command;
/**
 * takes the remainder when the first parameter is divided by the second parameter
 */
public class RemainderCommand implements Command {
	private double input1;
	private double input2;
	
	public RemainderCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	/**
	 * returns the remainder
	 */
	public double execute(){
		return (input1%input2);
	}
}
