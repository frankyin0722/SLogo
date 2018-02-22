package parser;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser implements TreeGenerator{
	private static final String Syntax = "Syntax";
	private HashMap<Pattern, CommandTypes> inputHandlerMap;
	private List<String> userInput;
	private int currentIndex;
	private int ListStartIndex = 0;
	private int ListEndIndex = 0;
	private PatternManager SomePatternManager;
	private CommandInitializerType commandInitializer; 
	
	private void generateInputHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternMapping = SomePatternManager.getPatterns(Syntax);
		inputHandlerMap = new HashMap<Pattern, CommandTypes>();
		for (Entry<String, Pattern> pattern : syntaxPatternMapping) {
			String type = pattern.getKey();
			try {
				Class<?> myInstance = Class.forName("parser." + type
						+ "Type");
				Constructor<?> constructor = myInstance
						.getConstructor(new Class[] { TreeGenerator.class,
								List.class });
				CommandTypes myCommandTypes = (CommandTypes) constructor.newInstance(userInput, (TreeGenerator) this);
				if (type.equals("Command")) {
					commandInitializer = new CommandInitializerType(userInput, (TreeGenerator) this);
					inputHandlerMap.put(pattern.getValue(), commandInitializer);
				} else
					inputHandlerMap.put(pattern.getValue(), myCommandTypes);

			} catch (InstantiationException | InvocationTargetException| IllegalAccessException | NoSuchMethodException | IllegalArgumentException | ClassNotFoundException e) {
				System.err.println("Error parsing the user-input command: Given Command Not Found. Please Enter A Correct Command!");
			}
		}
	}
	
	@Override
	public void increaseIndex() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseListStartIndex() {
		ListStartIndex++;
	}

	@Override
	public void increaseListEndIndex() {
		ListEndIndex++;
	}

	@Override
	public void recurse(CommandNode commandNode) {
		// TODO Auto-generated method stub
	}
	
}
