package command.Math;

import command.Command;

public class CosineCommand implements Command{
	private double input1;
	public CosineCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.cos(input1);
	}
}
