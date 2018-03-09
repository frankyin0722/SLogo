package command;

import alerts.Alerts;

/**
 * Class contains factory method to return a Command class type for reflection purposes
 * @author FY
 *
 */
public class CommandManager {
	private static String commandEnd = "Command";
	private static String commandPackage = "command";
	
	public Class<?> createCommand(String commandCategory, String command){
		Class<?> commandType = null;
		try {
			
			commandType = Class.forName(commandPackage + "." + commandCategory + "." + command + commandEnd);
		}
		catch (ClassNotFoundException e){
			Alerts.createAlert(e, "Class not found");
			System.err.println("Error initializing Command Object: Given Command Not Found. Please Enter A Correct Command!");
		}
		return commandType;
	}	
	
}
