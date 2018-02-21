package command.Boolean;

public class NotCommand {
	private double input1;
	
	public NotCommand (double test1){
		input1 = test1;
	}
	
	public double execute(){
		return (input1==0) ? 1:0;
	}
	
}
