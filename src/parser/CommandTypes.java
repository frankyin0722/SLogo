package parser;
/**
 * an interface for all command types, which requires all extended classes to implement the recurse method that determines the logic to recursively parse the user input command 
 */
public interface CommandTypes {
	
	/**
	 * recursively calls to parse the user input command into one or more command trees by adding child nodes to the current root node in such a form that could be unraveled/interpreted by the command tree interpreter 
	 * @param node: the command node that the recurse method is currently running on to add child nodes to form a tree structure 
	 */
	public void recurse (CommandNode node);
	
	/**
	 * gets the exact current command type that the recursive parsing is iterating through 
	 * @return
	 */
	public String whichType();
	
}
