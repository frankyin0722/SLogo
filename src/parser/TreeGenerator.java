package parser;

import interpreter.CommandTreeInterpreter;
/**
 * an interface that is extended by the parser class, implementing the key methods that the parser would need for its functionality in parsing user input commands 
 */
public interface TreeGenerator {
	
	/**
	 * increases the current index of the user input so that the recursion goes to look at the next user input word 
	 */
	public void increaseIndex();
	
	/**
	 * gets the current index of the user input so that the parser can check for out-of-bound cases 
	 * @return: integer of the current index of the user input 
	 */
	public int getIndex();
	
	/**
	 * increases the liststart index by one so that the layer of brackets can be tracked by user 
	 */
	public void increaseListStartIndex();
	
	/**
	 * increases the listend index by one so that the layer of brackets can be tracked by user 
	 */
	public void increaseListEndIndex();
	
	/**
	 * determines which command type should be called on the current index of the user input to continue the parsing recursion, and also throws an alert for user if no such command types can be found 
	 * @param commandNode: the command node that the recursive tree-generating process currently runs on 
	 */
	public void recurse(CommandNode commandNode);
	
	/**
	 * passes the interpreter to some commands that need the information stored in the interpreter 
	 */
	public CommandTreeInterpreter getInterpreter();
}
