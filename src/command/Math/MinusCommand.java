package command.Math;

public class MinusCommand {
	private double input1;
	
	public MinusCommand (double expr1){
		input1 = expr1;
	}
	
	public double execute(){
		return (-1* input1);
	}
}
