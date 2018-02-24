package interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.Command;
import command.CommandManager;
import parser.CommandNode;
import turtle.Turtle;
import variables.Variable;
import variables.VariableManager;

public class CommandTreeInterpreter {
	private CommandManager myCommandManager;
	private VariableManager myVariables;
	private int currentTurtle;
	private List<Turtle> myTurtles;
	private HashMap<String, List<CommandNode>> userDefinedCommands;
	private static int defaultTurtle = 0;
	
	public CommandTreeInterpreter() {
		myCommandManager = new CommandManager();
		myVariables = new VariableManager();
		currentTurtle = defaultTurtle;
		myTurtles = new ArrayList<>();
		userDefinedCommands = new HashMap<String, List<CommandNode>>();
	}
	
	public void interpretAllTrees(List<CommandNode> myRoots) {
		for (int i = 0; i < myRoots.size(); i++) {
			interpretTree(myRoots.get(i));
		}
	}
	
	public void interpretTree(CommandNode myRoot) {
		List<Object> Parameters = new ArrayList<>();
		System.out.println(myRoot.getCommandName());
		System.out.println(myRoot.getCommandType());
		System.out.println(myRoot.getNodeChildren().size());
		if (myRoot.getNodeChildren().size()!=0) {
			//if (!myRoot.getCommandType().equals("Bracket")) {
				for (CommandNode child : myRoot.getNodeChildren()) {
					interpretTree(child);
					if (myRoot.getCommandType().equals("Control")) {
						//System.out.println("!!!");
						Parameters.add(child);
					}
					else {
						//System.out.println("!!!");
						Parameters.add(child.getNodeValue());
						//System.out.println(child.getNodeValue());
					}
				}
			//}
		}
		updateNodeValue(myRoot, Parameters);
	}
	
	private void updateNodeValue(CommandNode node, List<Object> Parameters) {
		switch (node.getCommandType()) {
			case "Turtle":
				Parameters.add(myTurtles.get(currentTurtle));
				createCommand(node, Parameters);
				break;
			case "Control":
				Parameters.add(this);
				createCommand(node, Parameters);
				break;
			case "Constant":
				break;
			case "Variable":
				if (userDefinedCommands.containsKey(node.getCommandName())) {
					// run user command
				}
				if (!myVariables.checkVariable(node.getCommandName())) {
					Variable var = new Variable(0.0);
					myVariables.addVariable(var, node.getCommandName());
				}
				node.setNodeValue((double) myVariables.getVariable(node.getCommandName()).getValue());
				break;
			case "Bracket":
				node.setNodeValue(node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
				break;
			default: 
				createCommand(node, Parameters);
		}
	}

	private void createCommand(CommandNode node, List<Object> parameters) {
		Class<?> commandClass = myCommandManager.createCommand(node.getCommandType(), node.getCommandName());
		Constructor<?> commandConstructor = commandClass.getDeclaredConstructors()[0];
		
		Command thisCommand = null;
		try {
			thisCommand = (Command) commandConstructor.newInstance(parameters.toArray());
		} catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.err.print("Error creating commands: " + thisCommand.getClass().getName());
		}
		
		Method thisExecution = null;
		try {
			thisExecution = commandClass.getDeclaredMethod("execute", null);
			//System.out.println(thisCommand.toString());
			try {
				//System.out.println(thisCommand.toString());
				//System.out.println(parameters);
				double result = (double) thisExecution.invoke(thisCommand, null);
				node.setNodeValue(result);
				System.out.println(node.getCommandName() + ": " + node.getNodeValue());
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				System.err.println("Error executing commands: " + thisCommand.getClass().getName() + ".execute()");
			}
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			System.err.println("Error executing commands: " + thisCommand.getClass().getName() + ".execute()");
		} 
		
	}
	
	public List<Turtle> getTurtles() {
		return myTurtles;
	}
	
	public int getCurrentTurtle() {
		return currentTurtle;
	}
	
	public void setCurrentTurtle(int index) {
		currentTurtle = index;
	}
	
	public VariableManager getVariables() {
		return myVariables;
	}
	
	public HashMap<String, List<CommandNode>> getUserCommands(){
        return userDefinedCommands;
    }
	
}
