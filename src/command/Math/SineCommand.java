package command.Math;

public class SineCommand {
	private double input1;
	
	public SineCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.sin(input1);
	}
}
