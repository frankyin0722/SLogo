package command.Math;

public class TangentCommand {
	private double input1;
	
	public 
	TangentCommand (double expr1){
		input1 = Math.toRadians(expr1);
	}
	
	public double execute(){
		return Math.tan(input1);
	}
}
