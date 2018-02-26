package parser;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser implements TreeGenerator{
	private static final String Syntax = "resources.languages/Syntax";
	private HashMap<Pattern, CommandTypes> inputHandlerMap;
	private List<String> userInput;
	private int currentIndex;
	private int ListStartIndex = 0;
	private int ListEndIndex = 0;
	private PatternManager SomePatternManager = new PatternManager();
	private CommandType commandInitializer; 
	private String usedLanguage;
	
	public List<CommandNode> generateCommandTree(String input, String language) {
		try {
			currentIndex = 0;
			usedLanguage = language;
			userInput = Arrays.asList(input.split("\\s+"));
			generateInputHandlerMap();
			commandInitializer.initialize(language);
			while (getIndex() < userInput.size()) {
				System.out.println("Next command index: " + getIndex());
				commandInitializer.initialize(language);
			}
			System.out.println("parser ends");
			return commandInitializer.getRoot();
		} catch (NullPointerException e) {
			System.err.println("Error in parsing: No Input Command Found! ");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error in parsing: Unmatched Number of Brackets!");
		}
		return null;
	}

	private void generateInputHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternMapping = SomePatternManager.getPatterns(Syntax);
		inputHandlerMap = new HashMap<Pattern, CommandTypes>();
		for (Entry<String, Pattern> pattern : syntaxPatternMapping) {
			String type = pattern.getKey();
			try {
				if (existingCommandTypes(type)) {
					Class<?> myInstance = Class.forName("parser." + type
							+ "Type");
					Constructor<?> constructor = myInstance.getConstructor(new Class[] { List.class, TreeGenerator.class });
					CommandTypes myCommandTypes = (CommandTypes) constructor.newInstance(userInput, (TreeGenerator) this);
					if (type.equals("Command")) {
						System.out.println(type);
						commandInitializer = new CommandType(userInput, (TreeGenerator) this);
						inputHandlerMap.put(pattern.getValue(), commandInitializer);
					} else {
						inputHandlerMap.put(pattern.getValue(), myCommandTypes);
					}
				}
			} catch (InstantiationException | InvocationTargetException| IllegalAccessException | NoSuchMethodException | IllegalArgumentException | ClassNotFoundException e) {
				System.err.println("Error parsing the user-input command: Given Command Not Found. Please Enter A Correct Command!");
			}
		}
	}
	
	private boolean existingCommandTypes(String type) {
		return (!type.equals("ListEnd") && !type.equals("GroupEnd") && !type.equals("Comment") && !type.equals("Newline") && !type.equals("Whitespace"));
	}
	
	@Override
	public void recurse(CommandNode root) {
		//System.out.println(currentIndex);
		if (currentIndex >= userInput.size()) {
			return;
		}
		for (Pattern pattern : inputHandlerMap.keySet()) {
			if (SomePatternManager.match(userInput.get(currentIndex), pattern)) {
				CommandTypes cmdType = inputHandlerMap.get(pattern);
				//System.out.println(1);
				System.out.println(cmdType.toString());
				cmdType.recurse(root);
				break;
			}
		}
	}
	
	public HashMap<String, CommandNode> getMethods() {
		return commandInitializer.getMethods();
	}
	
	@Override
	public void increaseIndex() {
		currentIndex++;
	}

	@Override
	public int getIndex() {
		return currentIndex;
	}

	@Override
	public void increaseListStartIndex() {
		ListStartIndex++;
	}

	@Override
	public void increaseListEndIndex() {
		ListEndIndex++;
	}

	public void printNode(CommandNode node) {
		System.out.println("Type is: " + node.getCommandType());
		System.out.println(node.getCommandName());
		System.out.println("Root value is: " + node.getNodeValue());
		System.out.println("Current index is: " + getIndex());
		System.out.println();
	}
	
}
