package command.Math;

import command.Command;

public class MinusCommand implements Command{
	private double input1;
	
	public MinusCommand (double expr1){
		input1 = expr1;
	}
	
	public double execute(){
		return (-1* input1);
	}
}
