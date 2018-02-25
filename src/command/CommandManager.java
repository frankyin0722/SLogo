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
			System.out.println("command" + "." + commandCategory + "." + command + "Command");
			commandType = Class.forName("command" + "." + commandCategory + "." + command + "Command");
		}
		catch (ClassNotFoundException e){
			System.err.println("Error initializing Command Object: Given Command Not Found. Please Enter A Correct Command!");
		}
		return commandType;
	}	
	
}
