package command.Math;

public class NaturalLogCommand {
	private double input1;
	
	public NaturalLogCommand (double expr1){
		input1 = expr1;
	}
	
	public double execute(){
		return Math.log(input1);
	}
}
