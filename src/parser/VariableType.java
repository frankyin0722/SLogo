package parser;

import java.util.List;

public class VariableType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;

	public VariableType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex());
		CommandNode child = new CommandNode("Variable", currentValue, null, 0);
		node.addChild(child);
		myTreeGenerator.printNode(child);
		myTreeGenerator.increaseIndex();
	}
	
	public String whichType() {
		return "Variable";
	}
	
}
