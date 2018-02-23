package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CommandInitializerType implements CommandTypes {
	private static final int parameterIndex = 0; // stored in the 0th index of the array
	private static final int categoryIndex = 1; // stored in the 1st index of the array 
	private static final String userDefinedCommand = "MakeUserInstruction";
	private Map<String, String[]> parametersMapping;
	private List<Entry<String, Pattern>> languagePatternMapping;
	private boolean userDefinedInstruction;
	private List<String> userMethods = new ArrayList<>(); 
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private CommandNode myRoot;
	private PatternManager SomePatternManager;
	
	public CommandInitializerType (List<String> input, TreeGenerator treeGenerator) {
		myTreeGenerator = treeGenerator;
		userInput = input;
		makeParametersMapping();
	}
	
	public void initialize(String language) {
		languagePatternMapping = SomePatternManager.getPatterns(language);

		String nodeValue = getCommandFromLanguageBundle(userInput.get(myTreeGenerator.getIndex()));
		userDefinedInstruction = nodeValue.equals(userDefinedCommand);
		
		myRoot = new CommandNode(getCommandCategory(nodeValue), nodeValue, null, 0);
		
		
		
	}
	
	private String getCommandFromLanguageBundle(String input) {
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
	
	@Override
	public void recurse(CommandNode node) {
		// TODO Auto-generated method stub
		
	} 
	
}
