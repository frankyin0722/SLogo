package command.Math;

public class RandomCommand {
private double input1;
	
	public RandomCommand (double expr1){
		input1 = expr1;
	}
	
	public double execute(){
		return (Math.random() * input1);
	}
}
