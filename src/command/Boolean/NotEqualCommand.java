package command.Boolean;

import command.Command;
/**
 * checks that the two parameters are not equal
 */
public class NotEqualCommand implements Command{
	private double input1;
	private double input2;
	
	public NotEqualCommand (double test1, double test2){
		input1 = test1;
		input2 = test2;
	}
	/**
	 * returns 1 if not equal, else 0
	 */
	public double execute(){
		return (input1 != input2) ? 1:0;
	}
	
}
