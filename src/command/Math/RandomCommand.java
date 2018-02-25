package command.Math;

import command.Command;

public class RandomCommand implements Command {
private double input1;
	
	public RandomCommand (double expr1){
		input1 = expr1;
	}
	
	public double execute(){
		return (Math.random() * input1);
	}
}
