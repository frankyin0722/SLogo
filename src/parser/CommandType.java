package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CommandType implements CommandTypes {
	private static final int parameterIndex = 0; // stored in the 0th index of the array
	private static final int categoryIndex = 1; // stored in the 1st index of the array 
	private static final String userDefinedCommand = "MakeUserInstruction";
	private Map<String, String[]> parametersMapping;
	private List<Entry<String, Pattern>> languagePatternMapping;
	private boolean userDefinedInstruction;
	private List<String> userMethods = new ArrayList<>(); 
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private List<CommandNode> myRoots;
	private CommandNode myRoot;
	private PatternManager SomePatternManager = new PatternManager();;
	
	public CommandType (List<String> input, TreeGenerator treeGenerator) {
		myTreeGenerator = treeGenerator;
		userInput = input;
		makeParametersMapping();
		myRoots = new ArrayList<>();
		//System.out.println(userInput);
	}
	
	public void initialize(String language) {
		languagePatternMapping = SomePatternManager.getPatterns(language);
		String nodeValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex()));
		//System.out.println(myTreeGenerator.getIndex()+"!!!!!");
		// missing nodeValue
		//System.out.println(nodeValue);
		userDefinedInstruction = nodeValue.equals(userDefinedCommand);
		
		myRoot = new CommandNode(getCommandCategory(nodeValue), nodeValue, null, 0);
		//System.out.println(myRoot.getCommandName());
		myRoots.add(myRoot);
		//myTreeGenerator.printNode(myRoot);
		myTreeGenerator.printNode(myRoot);
		//System.out.println("Num Para Needed: " + getNumParameterNeeded(nodeValue));
		myTreeGenerator.increaseIndex();
		for (int i = 0; i < getNumParameterNeeded(nodeValue); i++) {
			myTreeGenerator.recurse(myRoot);
		}
		
	}
	
	private String getCommandFromLanguageBundle(String input) {
		//System.out.println(1);
		for (Entry<String, Pattern> pattern : languagePatternMapping) {
			if (SomePatternManager.match(input, pattern.getValue())) {
				return pattern.getKey();
			}
		}
		if (userDefinedInstruction) {
			userMethods.add(input);
			return input;
		}
		throw new IllegalArgumentException("Error converting language to Command: Command Not Found in Such Language!");

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
		return Integer.parseInt(parametersMapping.get(key)[parameterIndex]);
	}
	
	private String getCommandCategory(String key) {
		try {
			return parametersMapping.get(key)[categoryIndex];
		} catch (NullPointerException e){
			return "UserDefined";
		}
	}
	
	private void createUserDefinedInstruction(CommandNode root, String value) {
		CommandNode child = new CommandNode(getCommandCategory(value), value, null, 0);
		root.addChild(child);
		myTreeGenerator.recurse(root);
	}
	
	@Override
	public void recurse(CommandNode node) {
		String currentValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex())); // which parsed item the recursion is currently looking at 
		if (userDefinedInstruction) { // if the command type is user-defined command
			// userDefinedInstruction = false;
			createUserDefinedInstruction(node, currentValue);
			return;
		}
		CommandNode child = new CommandNode(getCommandCategory(currentValue), currentValue, null, 0);
		myTreeGenerator.printNode(child);
		myTreeGenerator.increaseIndex();
		node.addChild(child);
		for (int i = 0; i < getNumParameterNeeded(currentValue); i++) {
			myTreeGenerator.recurse(child);
		}
	} 
	
	public List<CommandNode> getRoot() {
		return myRoots;
	}
	
	public List<String> getMethods() {
		return userMethods;
	}
	
	public List<String> getUserInput(){
		return userInput;
	}
}
