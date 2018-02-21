package command.Math;

public class CosineCommand {
	private double input1;
	public CosineCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.sin(input1);
	}
}
