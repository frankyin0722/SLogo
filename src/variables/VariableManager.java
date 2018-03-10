package variables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import alerts.Alerts;
import alerts.CommandException;
import alerts.Resources;
/**
 * class for compactly containing all variables used 
 * @author shichengrao
 *
 */
public class VariableManager {
	private Map<String, Variable> variables;
	/**
	 * makes a new map of variables
	 */
	public VariableManager() {
		variables = new HashMap<>();
	}
	/**
	 * returns the variable with a given name, or CommandException if it doesn't exist.
	 * @param name key
	 * @return
	 */
    public Variable getVariable(String name) {
    		if(variables.containsKey(name)) {
    			return variables.get(name);
    		}
    		Alerts.createAlert(new CommandException(Resources.getString("VariableHeaderError")), "VariableMessageError");
    		throw new CommandException("Invalid Syntax");
    }
    /**
     * adds a new variable
     * @param var
     * @param name
     */
    public void addVariable(Variable var, String name) {
//    		if(name.charAt(0) == ':') {
//    			variables.put(name.substring(1), var);
//    		} else{
//    			variables.put(name, var);
//    		}
    		variables.put(name, var);
    }
    /**
     * checks if there is a variable associated with the name
     * @param name
     * @return
     */
    public boolean checkVariable(String name) {
    		return variables.containsKey(name);
    }
    /**
     * changes the variable that matches the name
     * @param data
     * @param name
     */
    public void setVariable(Object data, String name) {
    		variables.replace(name, new Variable(data));
    }
    /**
     * returns the names of all active variables
     * @return
     */
    public Set<String> getNames(){
    		return variables.keySet();
    }
    /**
     * changes the key for a variable from oldkey to newkey
     * @param oldkey
     * @param newkey
     */
    public void changeKey(String oldkey, String newkey) {
    		Variable oldvariable = variables.get(oldkey);
    		variables.remove(oldkey);
    		addVariable(oldvariable, newkey);
    }

    // this method returns a class type that is used to create variables, no need for now
//    public Class<?> createVariable(){
//    		
//    }

}
