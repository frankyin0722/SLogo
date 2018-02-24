package command.Math;

import command.Command;

public class SineCommand implements Command {
	private double input1;
	
	public SineCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.sin(input1);
	}
}
