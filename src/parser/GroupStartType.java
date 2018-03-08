package parser;

import java.util.ArrayList;
import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;

public class GroupStartType implements CommandTypes{
	private TreeGenerator myTreeGenerator;
	private List<String> userInput;
	private int BracketNum = 0;
	
	public GroupStartType(List<String> input, TreeGenerator treegenerator) {
		myTreeGenerator = treegenerator;
		userInput = input;
	}
	
	public void recurse(CommandNode node) {
		String currentValue = userInput.get(myTreeGenerator.getIndex()); // which parsed item the recursion is currently looking at 
		CommandNode child = new CommandNode("GroupBracket", currentValue + ":" + BracketNum++, null, 0);
		node.addChild(child); 
		myTreeGenerator.printNode(child);
		myTreeGenerator.increaseIndex();
		while (!userInput.get(myTreeGenerator.getIndex()).equals(")")) {
			myTreeGenerator.recurse(child);
		}
		System.out.println("group child size: "+child.getNodeChildren().size());
		if (child.getNodeChildren().size()!=0) {
			CommandNode unlimitedCommand = child.getNodeChildren().get(0); // get the first command that has unlimited property 
			int paramSize = unlimitedCommand.getNodeChildren().size();
			System.out.println("group command parameter number: "+paramSize);
			List<CommandNode> parameters = new ArrayList<CommandNode>();
			for (int i = 1; i < child.getNodeChildren().size(); i++) {
				parameters.add(child.getNodeChildren().get(i));
			}
			int otherCommandNum = (child.getNodeChildren().size()-1)/paramSize;
			for (int i = child.getNodeChildren().size()-1; i >= 1 ; i--) { // ??? 
				child.getNodeChildren().remove(i);
			}
			System.out.println("group child size after loop: "+child.getNodeChildren().size());
			if (((child.getNodeChildren().size()-1) % paramSize)!=0) { // check unlimited parameter validity (size match)
				Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError6");
				return;
			}
			
			System.out.println("rest command number: "+otherCommandNum);
			for (int i = 0; i < otherCommandNum; i++) {
				CommandNode otherCommand = new CommandNode(unlimitedCommand.getCommandType(),unlimitedCommand.getCommandName(),null,0);
				int paraCounter = 0;
				for (int j = 0; j < paramSize; j++) {
					otherCommand.addChild(parameters.get(paraCounter));
					paraCounter++;
				}
				child.addChild(otherCommand);
			}
			
		}
		myTreeGenerator.increaseIndex();
	}

	@Override
	public String whichType() {
		return "GroupStart";
	}
}
