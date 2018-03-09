package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import alerts.CommandException;

public class CommandType implements CommandTypes {
	private static final int PARAMETERINDEX = 0; // stored in the 0th index of the array
	private static final int CATEGORYINDEX = 1; // stored in the 1st index of the array 
	private Map<String, String[]> parametersMapping;
	private List<Entry<String, Pattern>> languagePatternMapping;
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private List<CommandNode> myRoots;
	private CommandNode myCurrentRoot;
	private PatternManager SomePatternManager = new PatternManager();
	private static String GroupStartCommand = "(";
	private static String ListStartName = "[";
	private static String ListStartType = "Bracket";
	private static String DefineCommand = "Define";
	private static String MakeUserInstructionCommand = "MakeUserInstruction";
	private static String UserDefinedType = "UserDefined";

	
	public CommandType (List<String> input, TreeGenerator treeGenerator) {
		myTreeGenerator = treeGenerator;
		userInput = input;
		makeParametersMapping();
		myRoots = new ArrayList<>();
	}
	
	public void initialize(ResourceBundle language) {
		languagePatternMapping = SomePatternManager.getPatterns(language);
		if (!isGroupStartCommand(userInput.get(myTreeGenerator.getIndex()))) {
			String nodeValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex()));
			myCurrentRoot = new CommandNode(getCommandCategory(nodeValue), nodeValue, null, 0);
			myRoots.add(myCurrentRoot);
			myTreeGenerator.printNode(myCurrentRoot);
			myTreeGenerator.increaseIndex();
			for (int i = 0; i < getNumParameterNeeded(nodeValue); i++) {
				myTreeGenerator.recurse(myCurrentRoot);
			}
		}
		else {
			myCurrentRoot = new CommandNode(ListStartType, ListStartName, null, 0);
			myRoots.add(myCurrentRoot);
			myTreeGenerator.recurse(myCurrentRoot);
		}
	}
	
	private boolean isGroupStartCommand(String value) {
		return value.equals(GroupStartCommand);
	}
	
	private boolean checkUserDefinedCommandValidity(String command) {
		try {
			String com = getCommandFromLanguageBundle(command);
			return !parametersMapping.containsKey(com);
		} catch (CommandException e){
			return true;
		}
	}
	
	private String getCommandFromLanguageBundle(String input) {
		if (myTreeGenerator.getInterpreter().getUserCommandParameters().containsKey(input)) {
			return input;
		}
		for (Entry<String, Pattern> pattern : languagePatternMapping) {
			if (SomePatternManager.match(input, pattern.getValue())) {
				return pattern.getKey();
			}
		}

		throw new CommandException("No command in language");
	}
	
	private void makeParametersMapping() {
		ResourceBundle resources = ResourceBundle.getBundle("parser/CommandParametersMapping");
		Enumeration<String> paramKeys = resources.getKeys();
		parametersMapping = new HashMap<>();
		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			parametersMapping.put(Key, resources.getString(Key).split(","));
		}
	}
	
	private int getNumParameterNeeded(String key) {
		if (parametersMapping.containsKey(key)) {
			return Integer.parseInt(parametersMapping.get(key)[PARAMETERINDEX]);
		}
		if (myTreeGenerator.getInterpreter().getUserCommandParameters().containsKey(key)) {
			return myTreeGenerator.getInterpreter().getUserCommandParameters().get(key).size(); // one bracket around all parameters of user-defined commands 
		}
		return 0;
	}
	
	private String getCommandCategory(String key) {
		try {
			return parametersMapping.get(key)[CATEGORYINDEX];
		} catch (NullPointerException e){
			try {
				if (myTreeGenerator.getInterpreter().getUserCommandParameters().containsKey(key)) {
					return "UserDefined";
				}
				return null;
			}
			catch (NullPointerException ee) {
				throw new IllegalArgumentException("Error in parsing: Command not defined!");
			}
		}
	}
	
	@Override
	public void recurse(CommandNode node) {
		if (node.getCommandName().equals(MakeUserInstructionCommand) || node.getCommandName().equals(DefineCommand)) {
			CommandNode child = new CommandNode(UserDefinedType, userInput.get(myTreeGenerator.getIndex()), null, 0); 
			if (checkUserDefinedCommandValidity(userInput.get(myTreeGenerator.getIndex()))) {
				 child.setNodeValue(1); 
			}
			node.addChild(child);
			myTreeGenerator.printNode(child);
			myTreeGenerator.increaseIndex();
		}
		else {
			String currentValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex())); 
			CommandNode child = new CommandNode(getCommandCategory(currentValue), currentValue, null, 0);
			myTreeGenerator.printNode(child);
			myTreeGenerator.increaseIndex();
			node.addChild(child);
			for (int i = 0; i < getNumParameterNeeded(currentValue); i++) {
				myTreeGenerator.recurse(child);
			}
		}
	} 
	
	public CommandNode getCurrentRoot() {
		return myCurrentRoot;
	}
	
	public List<CommandNode> getRoot() {
		return myRoots;
	}
	
	public List<String> getUserInput(){
		return Collections.unmodifiableList(userInput);
	}

	@Override
	public String whichType() {
		return "Command";
	}
}
