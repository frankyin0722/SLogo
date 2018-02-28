package command.Boolean;

import command.Command;

public class NotCommand implements Command {
	private double input1;
	
	public NotCommand (double test1){
		input1 = test1;
	}
	
	public double execute(){
		return (input1==0) ? 1:0;
	}
	
}
