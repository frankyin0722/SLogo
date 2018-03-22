package command.Math;

import command.Command;
/**
 * takes the arctangent of one parameter
 */
public class ArcTangentCommand implements Command{
	private double input1;
	
	public ArcTangentCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	/**
	 * returns the arctangent
	 */
	public double execute(){
		return Math.atan(input1);
	}
}
