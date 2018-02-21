package command.Boolean;

import command.Command;

public class AndCommand implements Command{
	private double input1;
	private double input2;
	
	public AndCommand (double test1, double test2){
		input1 = test1;
		input2 = test2;
	}
	
	public double execute(){
		return (input1!=0 && input2!=0) ? 1:0;
	}
	
}
