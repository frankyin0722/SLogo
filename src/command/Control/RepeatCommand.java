package command.Control;

import java.util.List;

import command.Command;
//purely speculative, will likely change a lot, but possible model for control commands
public class RepeatCommand implements Command{
	private double myNumTimes;
	private List<Command> myCommands;

	public RepeatCommand(double numTimes, List<Command> commands) {
		myNumTimes = numTimes;
		myCommands = commands;
	}

	@Override
	public double execute() {
		double returnValue = 0;
		while(myNumTimes > 0) {
			for(Command command: myCommands) {
				returnValue = command.execute();
			}
			myNumTimes--;
		}
		return returnValue;
	}
}
