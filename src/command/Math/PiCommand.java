package command.Math;

import command.Command;
/**
 * returns the math constant pi
 */
public class PiCommand implements Command{
	public PiCommand (){}
	/**
	 * returns pi
	 */
	public double execute(){
		return Math.PI;
	}
}
