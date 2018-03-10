package parser;

import java.util.ArrayList;
import java.util.List;
/**
 * a class built to support the tree structure of parsed commands, able to store the command name, command type, child nodes, and node value 
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
	 * adds one command node to this command node as its child 
	 * @param child: one child node of this command node 
	 */
	public void addChild(CommandNode child) {
		myChildren.add(child);
	}
	
	/**
	 * gets the command type of this node 
	 * @return: string of the command type 
	 */
	public String getCommandType() {
		return myCommandType;
	}
	/**
	 * gets the name of this node 
	 * @return: string of the command name 
	 */
	public String getCommandName() {
		return myCommandName;
	}

	/**
	 * gets the value of this node 
	 * @return: double of the node value 
	 */
	public double getNodeValue() {
		return myNodeValue;
	}
	/**
	 * sets the value of this node 
	 * @param newValue: the value that this node is assigned to 
	 */
	public void setNodeValue(double newValue) {
		myNodeValue = newValue;
	}

	/**
	 * passes the list of all the node's children for command parsing and interpreting purposes 
	 * @return: the list of all the node's child nodes 
	 */
	public List<CommandNode> getNodeChildren() {
		return myChildren;
	}
	
}
