package command;

/**
 * Interface from which all Command classes are inherited 
 * from, contains the abstract method to execute all types of commands
 * @author FY
 *
 */
@FunctionalInterface
public interface Command {
	
	public double execute();
	
}
