package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;

public class CommandType implements CommandTypes {
	private static final int parameterIndex = 0; // stored in the 0th index of the array
	private static final int categoryIndex = 1; // stored in the 1st index of the array 
	private static final String userDefinedCommand = "MakeUserInstruction";
	private Map<String, String[]> parametersMapping;
	private List<Entry<String, Pattern>> languagePatternMapping;
	//private boolean userDefinedInstruction;
	//private List<String> userMethods = new ArrayList<>(); 
	//private HashMap<String, CommandNode> userMethods = new HashMap<>();
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
		// check if it's user-defined method (Variable), if yes, deal with it differently 
		String nodeValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex()));
		System.out.println("look here: "+nodeValue);
		//userDefinedInstruction = nodeValue.equals(userDefinedCommand);
		
		myRoot = new CommandNode(getCommandCategory(nodeValue), nodeValue, null, 0);
		myRoots.add(myRoot);
		myTreeGenerator.printNode(myRoot);
		myTreeGenerator.increaseIndex();
		System.out.println(getNumParameterNeeded(nodeValue));
		for (int i = 0; i < getNumParameterNeeded(nodeValue); i++) {
			myTreeGenerator.recurse(myRoot);
		}
		
		/*if (userDefinedInstruction) { // if the current method is MakeUserInstruction 
			String methodName = myRoot.getNodeChildren().get(0).getCommandName();
			CommandNode methodRoot = new CommandNode(getCommandCategory(nodeValue), methodName, null, 0);
			methodRoot.addChild(myRoot.getNodeChildren().get(1));
			methodRoot.addChild(myRoot.getNodeChildren().get(2));
			if (userMethods.containsKey(methodName)) {
				userMethods.replace(methodName, methodRoot);
			}
			else {
				userMethods.put(methodName, methodRoot);
			}
		}*/
		
	}
	
	private String getCommandFromLanguageBundle(String input) {
		for (Entry<String, Pattern> pattern : languagePatternMapping) {
			if (SomePatternManager.match(input, pattern.getValue())) {
				System.out.println(pattern.getKey());
				return pattern.getKey();
				
			}
		}
		if (myTreeGenerator.getInterpreter().getUserCommands().containsKey(input)) {
			return input;
		}
		Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError");
		throw new CommandException("Invalid Syntax");
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
			return Integer.parseInt(parametersMapping.get(key)[parameterIndex]);
		}
		if (myTreeGenerator.getInterpreter().getUserCommandParameters().containsKey(key)) {
			//return myTreeGenerator.getInterpreter().getUserCommandParameters().size();
			return 1; // one bracket around all parameters of user-defined commands 
		}
		return 0;
	}
	
	private String getCommandCategory(String key) {
		try {
			return parametersMapping.get(key)[categoryIndex];
		} catch (NullPointerException e){
			try {
				if (myTreeGenerator.getInterpreter().getUserCommands().containsKey(key)) {
					return "UserDefined";
				}
				return null;
			}
			catch (NullPointerException ee) {
				throw new IllegalArgumentException("Error in parsing: Command not defined!");
			}
		}
	}
	
	/*private void createUserDefinedInstruction(CommandNode root, String value) {
		CommandNode child = new CommandNode(getCommandCategory(value), value, null, 0);
		root.addChild(child);
		myTreeGenerator.recurse(root);
	}*/
	
	@Override
	public void recurse(CommandNode node) {
		String currentValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex())); // which parsed item the recursion is currently looking at 
		/*if (userDefinedInstruction) { // if the command type is user-defined command
			// userDefinedInstruction = false;
			createUserDefinedInstruction(node, currentValue);
			return;
		}*/
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
	
	/*public HashMap<String, CommandNode> getMethods() {
		return userMethods;
	}*/
	
	public List<String> getUserInput(){
		return userInput;
	}

	@Override
	public String whichType() {
		return "Command";
	}
}
