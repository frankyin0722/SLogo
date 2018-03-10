package parser;

import java.util.List;
/**
 * implement the CommandTypes interface and one specific method for the recurse parsing on the liststart type 
 */
public class ListStartType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private int BracketNum = 0;
	
	public ListStartType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	/**
	 * recurses on the liststart type so that all of the sub-commands bracketed together are added as children to one common bracket node 
	 */
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
	
	@Override
	public String whichType() {
		return "ListStart";
	}
}
