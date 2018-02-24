package variables;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
	private Map<String, Variable> variables;
	
	public VariableManager() {
		variables = new HashMap<>();
	}
	//returns the variable with a given name, or NoSuchVariableException if it doesn't exist.
    public Variable getVariable(String name) {
    		//TODO: handle exception
    		return variables.get(name);
    }

    //adds a new variable. returns DuplicatedVariableException if the variable already exists
    public void addVariable(Variable var, String name) {
    		variables.put(name, var);
    }
    
    public boolean checkVariable(String name) {
    	return variables.containsKey(name);
    }
    
    //changes the variable that matches the name
    public void setVariable(Object data, String name) {
    		variables.replace(name, new Variable(data));
    }

    // this method returns a class type that is used to create variables, no need for now
//    public Class<?> createVariable(){
//    		
//    }

}
