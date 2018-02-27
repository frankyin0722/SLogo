package parser;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import interpreter.CommandTreeInterpreter;
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
	private int currentIndex = 0;
	private int ListStartIndex = 0;
	private int ListEndIndex = 0;
	private PatternManager SomePatternManager = new PatternManager();
	private CommandType commandInitializer; 
	private String usedLanguage;
	private CommandTreeInterpreter myInterpreter;
	
	public Parser(CommandTreeInterpreter interpreter) {
		myInterpreter = interpreter;
	}
	
	public List<CommandNode> generateCommandTree(String input, String language) {
		try {
			currentIndex = 0;
			usedLanguage = language;
			userInput = Arrays.asList(input.split("\\s+"));
			generateInputHandlerMap();
			commandInitializer.initialize(language);
			System.out.println("loooook here!!!: "+getIndex());
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
	
	/*public HashMap<String, CommandNode> generateUserDefinedMethods() {
		return getMethods();
	}*/
	
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
		
		if (myInterpreter.getUserCommands().containsKey(userInput.get(currentIndex))) {
			CommandNode userdefinedmethod = new CommandNode("UserDefined", userInput.get(currentIndex), null, 0);
			System.out.println("!!!!!!" + userInput.get(currentIndex));
			root.addChild(userdefinedmethod);
			CommandTypes parameter = new ListStartType(userInput, this);
			increaseIndex();
			parameter.recurse(userdefinedmethod);
		}
		
		/*if (myInterpreter.getUserCommands().containsKey(root.getCommandName())) {
			//CommandNode userdefinedmethod = new CommandNode("UserDefined", userInput.get(currentIndex), null, 0);
			//root.addChild(userdefinedmethod);
			//increaseIndex();
			List<CommandNode> para = myInterpreter.getUserCommandParameters().get(root.getCommandName());
			System.out.println("!!!!!!!!!!!!!!!" + para.size());
			increaseIndex();
			System.out.println("current index: " + getIndex());
			for (int i = 0; i < para.size(); i++) {
				for (Pattern pattern : inputHandlerMap.keySet()) {
					if (SomePatternManager.match(userInput.get(currentIndex), pattern)) {
						CommandTypes cmdType = inputHandlerMap.get(pattern);
						System.out.println(cmdType.toString());
						cmdType.recurse(root);
						break;
					}
				}
			}
		}*/
		else {
			for (Pattern pattern : inputHandlerMap.keySet()) {
				if (SomePatternManager.match(userInput.get(currentIndex), pattern)) {
					CommandTypes cmdType = inputHandlerMap.get(pattern);
					System.out.println(cmdType.toString());
					cmdType.recurse(root);
					break;
				}
			}
		}
	}
	//}
	
	/*public HashMap<String, CommandNode> getMethods() {
		return commandInitializer.getMethods();
	}*/
	
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
	
	public CommandTreeInterpreter getInterpreter() {
		return myInterpreter;
	}
	
	public void printNode(CommandNode node) {
		System.out.println("Type is: " + node.getCommandType());
		System.out.println(node.getCommandName());
		System.out.println("Root value is: " + node.getNodeValue());
		System.out.println("Current index is: " + getIndex());
		System.out.println();
	}
	
}
