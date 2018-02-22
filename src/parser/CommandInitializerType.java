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
	private static final int typeIndex = 1; // stored in the 1st index of the array 
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
//		String nodeValue = useLanguageBundle(userInput.get(myTreeGenerator.getIndex()));
		
	}
//	
//	private String useLanguageBundle(String input) {
//
//	}
	
	private void makeParametersMapping() {
		ResourceBundle resources = ResourceBundle.getBundle("parser/CommandParametersMapping");
		Enumeration<String> paramKeys = resources.getKeys();
		parametersMapping = new HashMap<>();
		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			parametersMapping.put(Key, resources.getString(Key).split(","));
		}
	}
	
	@Override
	public void recurse(CommandNode node) {
		// TODO Auto-generated method stub
		
	} 
	
}
