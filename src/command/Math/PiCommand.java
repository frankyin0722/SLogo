package command.Math;

import command.Command;
/**
 * returns the math constant pi
 */
public class PiCommand implements Command{
	/**
	 * no parameters, so no need to store anything
	 */
	public PiCommand (){}
	/**
	 * returns pi
	 */
	public double execute(){
		return Math.PI;
	}
}
