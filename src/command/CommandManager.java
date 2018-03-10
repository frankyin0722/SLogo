package command;

import alerts.Alerts;

/**
 * Class contains factory method to return a Command class type for reflection purposes
 */
public class CommandManager {
	private static String commandEnd = "Command";
	private static String commandPackage = "command";
	
	/**
	 * 
	 * @param commandCategory: the category of the current command (Turtle, Math, etc.)
	 * @param command: the name of the current command 
	 * @return: returns the wanted class by reflection 
	 */
	public Class<?> createCommand(String commandCategory, String command){
		Class<?> commandType = null;
		try {
			commandType = Class.forName(commandPackage + "." + commandCategory + "." + command + commandEnd);
		}
		catch (ClassNotFoundException e){
			Alerts.createAlert(e, "CommandMessageError2");
			System.err.println("Error initializing Command Object: Given Command Not Found. Please Enter A Correct Command!");
		}
		return commandType;
	}	
	
}
