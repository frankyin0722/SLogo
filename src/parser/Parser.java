package parser;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

//import sun.security.tools.keytool.Resources;

import interpreter.CommandTreeInterpreter;
import javafx.collections.ObservableList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser implements TreeGenerator{
	private static final ResourceBundle Syntax = ResourceBundle.getBundle("resources.languages/Syntax");
//	private static final ResourceBundle Syntax = Resources.getBundle("resources.languages/Syntax");
	private HashMap<Pattern, CommandTypes> inputHandlerMap;
	private List<String> userInput;
	private int currentIndex = 0;
	private int ListStartIndex = 0;
	private int ListEndIndex = 0;
	private PatternManager SomePatternManager = new PatternManager();
	private CommandType commandInitializer; 
	private ResourceBundle usedLanguage;
	private CommandTreeInterpreter myInterpreter;
	
	public Parser(CommandTreeInterpreter interpreter) {
		myInterpreter = interpreter;
	}
	
	private void parseInput(String input) {
		List<String> parsebylines = Arrays.asList(input.split("\n"));
		List<String> commentsprocessedout = new ArrayList<String>();
		userInput = new ArrayList<String>();
		for (int i = 0; i < parsebylines.size(); i++) {
			if (parsebylines.get(i).indexOf("#")==-1 && !parsebylines.get(i).equals("")) {
				commentsprocessedout.add(parsebylines.get(i).trim());
			}
		}
		for (int i = 0; i < commentsprocessedout.size(); i++) {
			List<String> parsebyword = Arrays.asList(commentsprocessedout.get(i).split("\\s+"));
			userInput.addAll(parsebyword);
		}
	}
	
	public void generateCommandTree(String input, ResourceBundle language) {
		try {
			currentIndex = 0;
			usedLanguage = language;
			//userInput = Arrays.asList(input.split("\\s+"));
			parseInput(input);
			generateInputHandlerMap();
			System.out.println("loooook here!!!: "+getIndex());
			while (getIndex() < userInput.size()) {
				System.out.println("Next command index: " + getIndex());
				commandInitializer.initialize(language);
				myInterpreter.interpretTree(commandInitializer.getCurrentRoot());
//				myInterpreter.iterateUDC(myInterpreter.getUserCommands());
			}
			System.out.println("parser ends");
			//return commandInitializer.getRoot();
		} catch (NullPointerException e) {
			System.err.println("Error in parsing: No Input Command Found! ");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error in parsing: Unmatched Number of Brackets!");
		}
		//return null;
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
		if (currentIndex >= userInput.size()) {
			return;
		}
		/*if (!root.getCommandName().equals("MakeUserInstruction") && myInterpreter.getUserCommands().containsKey(userInput.get(currentIndex))) {
			// if it is not MakeUserInstruction command, consider already-existing commands as commands;
			// if it is MakeUserInstruction command, consider already-existing commands as variables to be assigned with user-defined methods 
			CommandNode userdefinedmethod = new CommandNode("UserDefined", userInput.get(currentIndex), null, 0);
			System.out.println("!!!!!!" + userInput.get(currentIndex));
			root.addChild(userdefinedmethod);
			CommandTypes parameter = new ListStartType(userInput, this);
			increaseIndex();
			parameter.recurse(userdefinedmethod);
		}*/
		//else {
			for (Pattern pattern : inputHandlerMap.keySet()) {
				if (SomePatternManager.match(userInput.get(currentIndex), pattern)) {
					CommandTypes cmdType = inputHandlerMap.get(pattern);
					System.out.println(cmdType.toString());
					cmdType.recurse(root);
					break;
				}
			}
		//}
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
