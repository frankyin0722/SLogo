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
		myChildren = subCommands;
		myChildren = new ArrayList<CommandNode>();
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
	
	@Override
    public boolean equals (Object obj) {
        if (obj == null) {
        	return false; 
        }
        if (getClass() != obj.getClass()) {
        	return false;
        }
        return true;
    }
	
	public List<CommandNode> getNodeChildren() {
		return myChildren;
	}
	
}
