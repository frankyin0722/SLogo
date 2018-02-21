package command;

import java.util.HashMap;

/**
 * Class contains factory method to return a Command class type for reflection purposes
 * @author FY
 *
 */
public class CommandManager {
	
	public Class<?> createCommand(String commandCategory, String command){
		Class<?> commandType = null;
		try {
			commandType = Class.forName(commandCategory + "." + command + "Command");
		}
		catch (ClassNotFoundException e){
			System.err.println("Error initializing Command Object: Given Command Not Found. Please Enter A Correct Command!");
		}
		return commandType;
	}	
	
}
