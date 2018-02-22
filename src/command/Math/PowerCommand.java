package command.Math;

public class PowerCommand {
	private double input1;
	private double input2;
	
	public PowerCommand (double expr1, double expr2){
		input1 = expr1;
		input2 = expr2;
	}
	
	public double execute(){
		return Math.pow(input1, input2);
	}
}
