package command.Math;

import command.Command;
/**
 * takes the tangent of one parameter
 */
public class TangentCommand implements Command {
	private double input1;
	
	public 
	TangentCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	/**
	 * returns the tangent
	 */
	public double execute(){
		return Math.tan(input1);
	}
}
