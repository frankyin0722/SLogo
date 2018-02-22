package parser;

import java.util.ArrayList;
import java.util.List;

public class CommandNode {
	private String myCommandType;
	private String myCommandName;
	private List<CommandNode> myChildren;
	private double myNodeValue;
	
	public CommandNode (String commandType, String commandName, List<CommandNode> subCommands, double value) {
		myCommandType = commandType;
		myCommandName = commandName;
		myChildren = new ArrayList<CommandNode>();
		myChildren = subCommands;
		myNodeValue = value;
	}
	
	public void addChild(CommandNode child) {
		myChildren.add(child);
	}
	
	public String getCommandType() {
		return myCommandType;
	}
	
	public String getCommandName() {
		return myCommandName;
	}
	
	public double getNodeValue() {
		return myNodeValue;
	}
	
	public void setNodeValue(double newValue) {
		myNodeValue = newValue;
	}
	
	public List<CommandNode> getNodeChildren() {
		return myChildren;
	}
	
}
