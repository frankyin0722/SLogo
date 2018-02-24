package command.Math;

import command.Command;

public class RemainderCommand implements Command {
	private double input1;
	private double input2;
	
	public RemainderCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	
	public double execute(){
		return (input1%input2);
	}
}
