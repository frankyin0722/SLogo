package parser;

import java.util.List;
/**
 * implement the CommandTypes interface and one specific method for the recurse parsing on the constant type 
 */
public class ConstantType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	public static final String CONSTANT = "Constant";
	
	public ConstantType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	/**
	 * creates the constant node based on the current user input the parser is looking at, adds it to its parent command node, and increments the index to the next user input 
	 */
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex());
		CommandNode child = new CommandNode(CONSTANT, CONSTANT, null, Double.parseDouble(currentValue));
		node.addChild(child);
		myTreeGenerator.increaseIndex();
	}
	
	public String whichType() {
		return CONSTANT;
	}
	
}
