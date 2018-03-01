package command.Boolean;

import command.Command;
/**
 * takes the logical and operator on two doubles
 */
public class AndCommand implements Command{
	private double input1;
	private double input2;
	
	public AndCommand (double test1, double test2){
		input1 = test1;
		input2 = test2;
	}
	/**
	 * returns 1 if both are not 0, else 0
	 */
	public double execute(){
		return (input1!=0 && input2!=0) ? 1:0;
	}
	
}
