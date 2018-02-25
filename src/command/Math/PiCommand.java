package command.Math;

import command.Command;

public class PiCommand implements Command{
	public PiCommand (){}
	public double execute(){
		return Math.PI;
	}
}
