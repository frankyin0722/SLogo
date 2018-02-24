package parser;

import java.util.List;

public class ConstantType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	
	public ConstantType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex()); // which parsed item the recursion is currently looking at 
		//System.out.println(myTreeGenerator.getIndex());
		CommandNode child = new CommandNode("Constant", "Constant", null, Double.parseDouble(currentValue));
		node.addChild(child);
		myTreeGenerator.printNode(child);
		myTreeGenerator.increaseIndex();
		/*if (myTreeGenerator.getIndex() <= userInput.size()) {
			myTreeGenerator.recurse(node);
		}*/
	}
}
