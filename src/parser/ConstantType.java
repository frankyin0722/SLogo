package parser;

import java.util.List;

public class ConstantType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	public static final String CONSTANT = "Constant";
	public ConstantType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex()); // which parsed item the recursion is currently looking at 
		//
		CommandNode child = new CommandNode(CONSTANT, CONSTANT, null, Double.parseDouble(currentValue));
		node.addChild(child);
		myTreeGenerator.printNode(child);
		myTreeGenerator.increaseIndex();
	}
	
	public String whichType() {
		return CONSTANT;
	}
	
}
