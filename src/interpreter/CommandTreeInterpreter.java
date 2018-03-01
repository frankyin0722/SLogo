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
	private List<Listener> theseListeners;
	
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
	}
	
	public void interpretTree(CommandNode myRoot) {
		List<Object> Parameters = new ArrayList<>();
		if (myRoot.getNodeChildren().size()!=0) {
			for (int i = 0; i < myRoot.getNodeChildren().size(); i ++) {
				if (myRoot.getCommandType().equals("Control")) {
					if (i == 0 && !myRoot.getCommandName().equals("MakeUserInstruction")) {
						interpretTree(myRoot.getNodeChildren().get(i));
					}
					Parameters.add(myRoot.getNodeChildren().get(i));
				}
				else {
					interpretTree(myRoot.getNodeChildren().get(i));
					Parameters.add(myRoot.getNodeChildren().get(i).getNodeValue());
				}
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
				}
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
				if (!myVariables.checkVariable(node.getCommandName())) {
					Variable newvar = new Variable((double) 0);
					myVariables.addVariable(newvar, node.getCommandName());
				}
				node.setNodeValue((double) myVariables.getVariable(node.getCommandName()).getValue());
				break;
			case "Bracket":
				if (node.getNodeChildren().size()!=0) {
					node.setNodeValue(node.getNodeChildren().get(node.getNodeChildren().size()-1).getNodeValue());
				}
				node.setNodeValue(0.0);
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
			try {
				double result = (double) thisExecution.invoke(thisCommand, null);
				node.setNodeValue(result);
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
	
	public void printExistingCommands() {
		for (String commandname : userDefinedCommands.keySet()) {
			CommandNode methodRoot = userDefinedCommands.get(commandname);
		}
	}
	
}
