package command.Math;

import command.Command;
/**
 * returns the math constant pi
 */
public class PiCommand implements Command{
	
	public PiCommand (){
		/**
		 * no parameters, so no need to store anything
		 */
	}
	/**
	 * returns pi
	 */
	public double execute(){
		return Math.PI;
	}
}
