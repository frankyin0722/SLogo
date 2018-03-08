package interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
import command.Command;
import command.CommandManager;
import observables.Listener;
import parser.CommandNode;
import turtle.Turtle;
import turtle.TurtleController;
import variables.Variable;
import variables.VariableManager;

public class CommandTreeInterpreter {
	private CommandManager myCommandManager;
	private VariableManager myVariables;
	private TurtleController myTurtleController;
	//private List<Turtle> myTurtles;
	private HashMap<String, CommandNode> userDefinedCommands;
	private HashMap<String, List<CommandNode>> userDefinedCommandParameters;
	private static int defaultTurtle = 1;
	private ArrayList<String> history;
	private HashMap<String, String> activeUDC;
	private List<Listener> theseListeners;
	private List<Listener> activeUDCListener;
	//private List<Integer> activeTurtles;
	private int currentTurtle; 

	
	public CommandTreeInterpreter(TurtleController turtlecontroller) {
		myCommandManager = new CommandManager();
		myVariables = new VariableManager();
		myTurtleController = turtlecontroller;
		currentTurtle = defaultTurtle;
		userDefinedCommands = new HashMap<String, CommandNode>();
		userDefinedCommandParameters = new HashMap<String, List<CommandNode>>();
		history = new ArrayList<String>();
		theseListeners = new ArrayList<Listener>();
		activeUDC = new HashMap<>();
		activeUDCListener = new ArrayList<Listener>();
	}
	
	public void interpretTree(CommandNode myRoot) {
		List<Object> Parameters = new ArrayList<>();
		for (int i = 0; i < myRoot.getNodeChildren().size(); i ++) {
			switch(myRoot.getCommandType()) {
			case "Control":
				if (i == 0 && !myRoot.getCommandName().equals("MakeUserInstruction") && !myRoot.getCommandName().equals("AskWith") && !myRoot.getCommandName().equals("Define")) {
					interpretTree(myRoot.getNodeChildren().get(i));
				}
				Parameters.add(myRoot.getNodeChildren().get(i));
				break;
			case "Turtle":
				for (int t = 0; t < myTurtleController.getActiveTurtleIndices().size(); t++) {
					currentTurtle = myTurtleController.getActiveTurtleIndices().get(t); // 0 index refers to 1 turtle 
					interpretTree(myRoot.getNodeChildren().get(i));
					Parameters.add(myRoot.getNodeChildren().get(i).getNodeValue());
				}
				if (myTurtleController.getActiveTurtleIndices().size()==0) {
					currentTurtle = 0;
				}
				else {
					currentTurtle = myTurtleController.getActiveTurtleIndices().get(0); // reset the current turtle to be the first in active turtle list 
				}
				break;
			default:
				interpretTree(myRoot.getNodeChildren().get(i));
				Parameters.add(myRoot.getNodeChildren().get(i).getNodeValue());
				break;
			}
		}
	
		updateNodeValue(myRoot, Parameters);
	}
	
	private void updateNodeValue(CommandNode node, List<Object> Parameters) {
		switch (node.getCommandType()) {
			case "UserDefined":
				List<CommandNode> para = userDefinedCommandParameters.get(node.getCommandName());
				for (int i = 0; i < para.size(); i++) {
					if (myVariables.checkVariable(para.get(i).getCommandName())) {
						myVariables.setVariable((double) Parameters.get(i), para.get(i).getCommandName());
					}
					else {
						myVariables.addVariable(new Variable((double) Parameters.get(i)), para.get(i).getCommandName());
					}	
					if (i==2) {
						System.out.println("current depth value: "+Parameters.get(i));
					}
				}
				List<Object> paramForUserDefinedCommand = new ArrayList<Object>();
				String myDefinedCommandName = node.getCommandName();
				paramForUserDefinedCommand.add(myDefinedCommandName);
				paramForUserDefinedCommand.add(this);
				createCommand(node,paramForUserDefinedCommand);
				break;
			case "Turtle":
				int individualParameterSize = node.getNodeChildren().size();
				int paramCount = 0; 
				for (int i = 0; i < myTurtleController.getActiveTurtleIndices().size(); i++) {
					List<Object> individualParameter = new ArrayList<Object>();
					for (int j = 0; j < individualParameterSize; j++) {
						individualParameter.add(Parameters.get(paramCount));
						paramCount++;
					}
					individualParameter.add(myTurtleController.getTurtle(myTurtleController.getActiveTurtleIndices().get(i)-1));
					createCommand(node,individualParameter);
				}
				break;
			case "Control":
				Parameters.add(this);
				createCommand(node, Parameters);
				break;
			case "Constant":
				break;
			case "Variable":
				if (!myVariables.checkVariable(node.getCommandName())) {
					Variable newvar = new Variable((double) 0);
					myVariables.addVariable(newvar, node.getCommandName());
				}
				node.setNodeValue((double) myVariables.getVariable(node.getCommandName()).getValue());
				break;
			case "Bracket":
				if (node.getNodeChildren().size()!=0) {
					System.out.println("user defined command nodevalue: "+node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
					node.setNodeValue(node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
				}
				else {
					node.setNodeValue(0.0);
				}
				break;
			case "GroupBracket":
				if (node.getNodeChildren().size()!=0) {
					//System.out.println("user defined command nodevalue: "+node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
					double totalValue = 0;
					System.out.println(node.getNodeChildren().size());
					for (CommandNode sub : node.getNodeChildren()) {
						totalValue = totalValue + sub.getNodeValue();
					}
					node.setNodeValue(totalValue);
				}
				else {
					node.setNodeValue(0.0);
				}
				System.out.println(node.getNodeValue());
				break;
			default: 
				createCommand(node, Parameters);
				break;
		}
	}

	private void createCommand(CommandNode node, List<Object> parameters) {
		Class<?> commandClass = null;
		if (!node.getCommandType().equals("UserDefined")) {
			commandClass = myCommandManager.createCommand(node.getCommandType(), node.getCommandName());
		}
		else {
			commandClass = myCommandManager.createCommand(node.getCommandType(), node.getCommandType());
		}
		Constructor<?> commandConstructor = commandClass.getDeclaredConstructors()[0];
		Command thisCommand = null;
		try {
			thisCommand = (Command) commandConstructor.newInstance(parameters.toArray());
		} catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError1");
			return;
		}
		Method thisExecution = null;
		try {
			thisExecution = commandClass.getDeclaredMethod("execute", null);
			try {
				double result = (double) thisExecution.invoke(thisCommand, null);
				node.setNodeValue(result);
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				System.err.println("Error executing commands: " + thisCommand.getClass().getName() + ".execute()");
			}
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			System.err.println("Error executing commands1: " + thisCommand.getClass().getName() + ".execute()");
		} 
		
	}
	
	public List<Turtle> getCurrentActiveTurtles() {
		return myTurtleController.getActiveTurtles();
	}
	
	public List<Integer> getCurrentActiveTurtleIndices() {
		return myTurtleController.getActiveTurtleIndices();
	}
	
	/*public void setCurrentActiveTurtleIndices(List<Integer> newactiveindices) {
		activeTurtles = newactiveindices;
	}*/
	public TurtleController getTurtleController() {
		return myTurtleController;
	}
	
	public List<Turtle> getCurrentAvailableTurtles() {
		return myTurtleController.getAllTurtles();
	}
	
	public VariableManager getVariables() {
		return myVariables;
	}
	
	public HashMap<String, CommandNode> getUserCommands(){
        return userDefinedCommands;
    }
	
	public HashMap<String, List<CommandNode>> getUserCommandParameters(){
        return userDefinedCommandParameters;
    }
	
	public void addToHistory(String command) {
		history.add(command);
		notifyListeners();
	}
	
	public ArrayList<String> getHistory(){
		return history;
	}

	private void notifyListeners() {
		for(Listener l:theseListeners) {
			l.update();
		}
	}

	public void addListener(Listener l) {
		theseListeners.add(l);
	}
	
	public void printExistingCommands() {
		for (String commandname : userDefinedCommands.keySet()) {
			CommandNode methodRoot = userDefinedCommands.get(commandname);
		}
	}
	
	public void iterateUDC(HashMap<String, CommandNode> map) {
//		System.out.print("XXXXX at iterateUDC");
		for (String key: map.keySet()) {
			CommandNode command = map.get(key);
			addToActiveUDC(key, "");
//			addToActiveUDC(key, iterateNode(command));
		}
	}
		
	public int getCurrentActiveTurtleIndex() {
		return currentTurtle;
	}
	
	public void setCurrentActiveTurtleIndex(int index) {
		currentTurtle = index;
	}
	
	public String iterateNode(CommandNode node) {
		return node.getCommandName();
	}
	
	public void addToActiveUDC(String commandName, String commandAction) {
//		if (activeUDC.get(commandName) == null) {
			activeUDC.put(commandName, commandAction);
//			System.out.print("XXXXX addToActive UDC" + commandName + commandAction);
			notifyUDCListeners();
//		}
	}
	
	public void addUDCListener(Listener l) {
		activeUDCListener.add(l);
	}
	
	public void notifyUDCListeners() {
		for (Listener l: activeUDCListener) {
			l.update();
		}
	}
	
	public HashMap<String, String> getActiveUDC() {
		return activeUDC;
	}
	
	public List<Listener> getActiveUDCListener() {
		return activeUDCListener;
	}

}
