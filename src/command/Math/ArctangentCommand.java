package command.Math;

public class ArctangentCommand {
	private double input1;
	
	public ArctangentCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.atan(input1);
	}
}
