package parser;

import java.util.ArrayList;
import java.util.List;
/**
 * A command node for a tree, holding its value, type, name, and children
 * @author FY
 *
 */
public class CommandNode {
	private String myCommandType;
	private String myCommandName;
	private List<CommandNode> myChildren;
	private double myNodeValue;
	/**
	 * Makes a command node with given parameters
	 * @param commandType
	 * @param commandName
	 * @param subCommands
	 * @param value
	 */
	public CommandNode (String commandType, String commandName, List<CommandNode> subCommands, double value) {
		myCommandType = commandType;
		myCommandName = commandName;
		myChildren = subCommands;
		myChildren = new ArrayList<CommandNode>();
		myNodeValue = value;
	}
	/**
	 * adds a child to the node's children
	 * @param child
	 */
	public void addChild(CommandNode child) {
		myChildren.add(child);
	}
	/**
	 * 
	 * @return type of node
	 */
	public String getCommandType() {
		return myCommandType;
	}
	/**
	 * 
	 * @return name of command
	 */
	public String getCommandName() {
		return myCommandName;
	}
	/**
	 * 
	 * @return value of node
	 */
	public double getNodeValue() {
		return myNodeValue;
	}
	/**
	 * set node value to newValue
	 * @param newValue
	 */
	public void setNodeValue(double newValue) {
		myNodeValue = newValue;
	}
	/**
	 * 
	 * @return children of node, in a list
	 */
	public List<CommandNode> getNodeChildren() {
		return myChildren;
	}
	
}
