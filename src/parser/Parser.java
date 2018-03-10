package parser;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;

import interpreter.CommandTreeInterpreter;
/**
 * parse the user input command to one or more command trees with a sequential logic of layers so that the interpreter can interpret the tree and execute commands in correct ordering 
 */
public class Parser implements TreeGenerator{
	private static final ResourceBundle Syntax = ResourceBundle.getBundle("resources.languages/Syntax");
	private HashMap<Pattern, CommandTypes> inputHandlerMap;
	private List<String> userInput;
	private int currentIndex = 0;
	private int ListStartIndex = 0;
	private int ListEndIndex = 0;
	private PatternManager SomePatternManager = new PatternManager();
	private CommandType commandInitializer; 
	private CommandTreeInterpreter myInterpreter;
	private static List<String> UnwantedCommandTypes = new ArrayList<String>() {{
		add("ListEnd");
		add("GroupEnd");
		add("Comment");
		add("Newline");
		add("Whitespace");
	}};
	
	public Parser(CommandTreeInterpreter interpreter) {
		myInterpreter = interpreter;
	}
	
	private void parseInput(String input) {
		List<String> parsebylines = Arrays.asList(input.split("\n"));
		List<String> commentsprocessedout = new ArrayList<>();
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
	
	/**
	 * generates the command tree from the user input command with respect to the currently selected language environment 
	 * @param input: user input 
	 * @param language: language package 
	 */
	public void generateCommandTree(String input, ResourceBundle language) {
		try {
			currentIndex = 0;
			parseInput(input);
			generateInputHandlerMap();
			while (getIndex() < userInput.size()) {
				commandInitializer.initialize(language);
				myInterpreter.interpretTree(commandInitializer.getCurrentRoot());
				myInterpreter.iterateUDC(myInterpreter.getUserCommands());
			}
		} catch (IndexOutOfBoundsException e) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError4");
			return;
		} catch (CommandException | NullPointerException e) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError3")), "CommandMessageError2");
			return;
		}
		
	}
	
	private void generateInputHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternMapping = SomePatternManager.getPatterns(Syntax);
		inputHandlerMap = new HashMap<Pattern, CommandTypes>();
		for (Entry<String, Pattern> pattern : syntaxPatternMapping) {
			String type = pattern.getKey();
			try {
				if (existingCommandTypes(type)) {
					createInputTypes(pattern, type);
				}
			} catch (InstantiationException | InvocationTargetException| IllegalAccessException | NoSuchMethodException | IllegalArgumentException | ClassNotFoundException e) {
				System.err.println("Error parsing the user-input command: Given Command Not Found. Please Enter A Correct Command!!!");
			}
		}
	}
	
	private void createInputTypes(Entry<String, Pattern> pattern, String type) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myInstance = Class.forName("parser." + type
				+ "Type");
		Constructor<?> constructor = myInstance.getConstructor(new Class[] { List.class, TreeGenerator.class });
		CommandTypes myCommandTypes = (CommandTypes) constructor.newInstance(userInput, (TreeGenerator) this);
		if (type.equals("Command")) {
			
			commandInitializer = new CommandType(userInput, (TreeGenerator) this);
			inputHandlerMap.put(pattern.getValue(), commandInitializer);
		} else {
			inputHandlerMap.put(pattern.getValue(), myCommandTypes);
		}
	}
	
	private boolean existingCommandTypes(String type) {
		return !UnwantedCommandTypes.contains(type);
	}
	
	/**
	 * determines which command type should be called on the current index of the user input to continue the parsing recursion, and also throws an alert for user if no such command types can be found 
	 */
	@Override
	public void recurse(CommandNode root) {
		if (currentIndex >= userInput.size()) {
			return;
		}
		boolean findSyntax = false;
		for (Pattern pattern : inputHandlerMap.keySet()) {
			if (SomePatternManager.match(userInput.get(currentIndex), pattern)) {
				findSyntax = true;
				CommandTypes cmdType = inputHandlerMap.get(pattern);
				cmdType.recurse(root);
				break;
			}
		}
		if (!findSyntax) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError1");
			throw new CommandException(Resources.getString("CommandHeaderError"));
		}
	}
	
	/**
	 * increases the current index of the user input command 
	 */
	@Override
	public void increaseIndex() {
		currentIndex++;
	}
	
	/**
	 * gets the current index of the user input command 
	 */
	@Override
	public int getIndex() {
		return currentIndex;
	}

	/**
	 * increases the liststart index by one so that the layer of brackets can be tracked by user 
	 */
	@Override
	public void increaseListStartIndex() {
		ListStartIndex++;
	}

	/**
	 * increases the listend index by one so that the layer of brackets can be tracked by user 
	 */
	@Override
	public void increaseListEndIndex() {
		ListEndIndex++;
	}
	
	/**
	 * passes the interpreter to some commands that need the information stored in the interpreter 
	 */
	public CommandTreeInterpreter getInterpreter() {
		return myInterpreter;
	}
	
}
