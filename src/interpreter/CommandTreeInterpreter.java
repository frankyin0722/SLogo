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
import javafx.collections.ObservableList;
import observables.Listener;
import parser.CommandNode;
import turtle.Turtle;
import variables.Variable;
import variables.VariableManager;

public class CommandTreeInterpreter {
	private CommandManager myCommandManager;
	private VariableManager myVariables;
	private int currentTurtle;
	private List<Turtle> myTurtles;
	private HashMap<String, CommandNode> userDefinedCommands;
	private HashMap<String, List<CommandNode>> userDefinedCommandParameters;
	private static int defaultTurtle = 0;
	private ArrayList<String> history;
	private HashMap<String, String> activeUDC;
	private List<Listener> theseListeners;
	private List<Listener> activeUDCListener;

	
	public CommandTreeInterpreter(Turtle turtle) {
		myCommandManager = new CommandManager();
		myVariables = new VariableManager();
		currentTurtle = defaultTurtle;
		myTurtles = new ArrayList<>();
		myTurtles.add(turtle);
		userDefinedCommands = new HashMap<String, CommandNode>();
		userDefinedCommandParameters = new HashMap<String, List<CommandNode>>();
		history = new ArrayList<String>();
		theseListeners = new ArrayList<Listener>();
		activeUDC = new HashMap<>();
		activeUDCListener = new ArrayList<Listener>();
	}
	
	public void interpretAllTrees(List<CommandNode> myRoots) {
		//mergeMethods(myMethods);
		//System.out.println("existing :expr command?: " + getUserCommands().containsKey(":expr"));
		for (int i = 0; i < myRoots.size(); i++) {
			interpretTree(myRoots.get(i));
		}
	}
	
	/*private void mergeMethods(HashMap<String, CommandNode> newmethods) {
		for (String key : newmethods.keySet()) {
			if (!myVariables.checkVariable(key)) {
				if (!userDefinedCommands.containsKey(key)) {
					userDefinedCommands.put(key, newmethods.get(key));
				}
				else {
					userDefinedCommands.replace(key, newmethods.get(key));
				}
			}
		}
		
	}*/
	
	public void interpretTree(CommandNode myRoot) {
		List<Object> Parameters = new ArrayList<>();
		System.out.println(myRoot.getCommandName());
		System.out.println(myRoot.getCommandType());
		System.out.println(myRoot.getNodeChildren().size());
		
		if (myRoot.getNodeChildren().size()!=0) {
			//if (!myRoot.getCommandType().equals("UserDefined")) {
				for (int i = 0; i < myRoot.getNodeChildren().size(); i ++) {
					if (myRoot.getCommandType().equals("Control")) {
						//MakeUserInstructionCase(myRoot);
						if (i == 0 && !myRoot.getCommandName().equals("MakeUserInstruction")) {
							interpretTree(myRoot.getNodeChildren().get(i));
							//System.out.println("if statement variable value: "+myRoot.getNodeValue());
						}
						System.out.println("herehere"+myRoot.getNodeChildren().get(i).getCommandName());
						System.out.println("herehere"+myRoot.getNodeChildren().get(i).getCommandType());
						Parameters.add(myRoot.getNodeChildren().get(i));
					}
					else {
						//System.out.println("!!!");						
						System.out.println("new print statement: " + myRoot.getNodeChildren().get(i).getCommandName());
						interpretTree(myRoot.getNodeChildren().get(i));
						Parameters.add(myRoot.getNodeChildren().get(i).getNodeValue());
						//System.out.println("if statement variable value: "+myRoot.getNodeValue());
					}
				}
			//}
		}
		updateNodeValue(myRoot, Parameters);
	}
	
	/*private void MakeUserInstructionCase(CommandNode myRoot) {
		if (userDefinedCommands.containsKey(myRoot.getNodeChildren().get(0).getCommandName())) {
			myRoot.setNodeValue(1);
		}
		else {
			myRoot.setNodeValue(0);
		}
	}*/
	
	private void updateNodeValue(CommandNode node, List<Object> Parameters) {
		switch (node.getCommandType()) {
			case "UserDefined":
				List<CommandNode> para = userDefinedCommandParameters.get(node.getCommandName());
				System.out.println("user-defined parameter size: " + para.size());
				/*if (node.getNodeChildren().get(0).getNodeChildren().size()!=para.size()) {
					throw new IllegalArgumentException("Error in executing user-defined method: Unmatched Number of Parameters!");
				}*/
				for (int i = 0; i < para.size(); i++) {
					if (myVariables.checkVariable(para.get(i).getCommandName())) {
						myVariables.setVariable((double) Parameters.get(i), para.get(i).getCommandName());
					}
					else {
						myVariables.addVariable(new Variable((double) Parameters.get(i)), para.get(i).getCommandName());
					}
					System.out.println(":expr value: " + myVariables.getVariable(para.get(i).getCommandName()).getValue());
				}
				System.out.println("inside my list: " + userDefinedCommands.get(node.getCommandName()).getCommandName());
				CommandNode storedMethod = userDefinedCommands.get(node.getCommandName());
				interpretTree(storedMethod);
				node.setNodeValue(storedMethod.getNodeValue());				
				break;
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
				//System.out.println(node.getCommandName());
				//System.out.println(myVariables.checkVariable(node.getCommandName()));
				if (!myVariables.checkVariable(node.getCommandName())) {
					
					Variable newvar = new Variable((double) 0);
					myVariables.addVariable(newvar, node.getCommandName());
				}
				node.setNodeValue((double) myVariables.getVariable(node.getCommandName()).getValue());
				//System.out.println("currentNode value: " + node.getNodeValue());
				break;
			case "Bracket":
				node.setNodeValue(node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
				break;
			default: 
				createCommand(node, Parameters);
				break;
		}
	}

	private void createCommand(CommandNode node, List<Object> parameters) {
		Class<?> commandClass = myCommandManager.createCommand(node.getCommandType(), node.getCommandName());
		Constructor<?> commandConstructor = commandClass.getDeclaredConstructors()[0];
		
		Command thisCommand = null;
		
		try {
			thisCommand = (Command) commandConstructor.newInstance(parameters.toArray());
		} catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			Alerts.createAlert(new CommandException(Resources.getString("CommandHeaderError")), "CommandMessageError1");
			throw new CommandException("Invalid Syntax");
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
	
	public Turtle getCurrentTurtle() {
		return myTurtles.get(currentTurtle);
	}
	
	public void setCurrentTurtle(int index) {
		currentTurtle = index;
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

	private void notifyListeners() {
		for(Listener l:theseListeners) {
			l.update();
		}
	}

	public void addListener(Listener l) {
		theseListeners.add(l);
	}
	public ArrayList<String> getHistory(){
		return history;
	}
	
	public void iterateUDC(HashMap<String, CommandNode> map) {
		System.out.print("XXXXX at iterateUDC");
		for (String key: map.keySet()) {
			CommandNode command = map.get(key);
			addToActiveUDC(key, iterateNode(command));
		}
	}
		
	public String iterateNode(CommandNode node) {
		return node.getCommandName();
	}
	
	public void addToActiveUDC(String commandName, String commandAction) {
		if (activeUDC.get(commandName) == null) {
			activeUDC.put(commandName, commandAction);
			System.out.print("XXXXX addToActive UDC" + commandName + commandAction);
			notifyUDCListeners();
		}
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
