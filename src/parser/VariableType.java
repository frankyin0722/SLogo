package parser;

import java.util.List;
/**
 * implement the CommandTypes interface and one specific method for the recurse parsing on the constant type 
 */
public class VariableType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;

	public VariableType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	/**
	 * makes new command node under given command node, with variable tag and the current value
	 */
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex());
		CommandNode child = new CommandNode("Variable", currentValue, null, 0);
		node.addChild(child);
		myTreeGenerator.increaseIndex();
	}
	/**
	 * returns type, which is variable
	 */
	public String whichType() {
		return "Variable";
	}
	
}
