package parser;

import java.util.List;

public class ListStartType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private int BracketNum = 0;
	
	public ListStartType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex()); // which parsed item the recursion is currently looking at 
		myTreeGenerator.increaseListStartIndex();
		CommandNode child = new CommandNode("Bracket", currentValue + ":" + BracketNum, null, 0);
		BracketNum++;
		node.addChild(child); 
		myTreeGenerator.increaseIndex();
		while (!userInput.get(myTreeGenerator.getIndex()).equals("]")) {
			myTreeGenerator.recurse(child);
		}
		myTreeGenerator.increaseIndex();
		myTreeGenerator.increaseListEndIndex();
	}
	
	public String whichType() {
		return "ListStart";
	}
}
