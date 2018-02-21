package variables;
// class exists in case of need for string/int/array variables. For now is only for doubles
public class Variable {
	private double value;
	
	public Variable(Object data) {
		value = (double) data;
	}
	// gives the type of the current variable
    public String getType() {
    		return "Double";
    }

    // returns the stored value 
    public Object getValue() {
    		return value;
    }

    //changes value to new value, throws TypeException if the type doesn't match the variable type. 
    public void setValue(Object newValue){
    	//TODO throw exception
    		value = (double) newValue;
    }

}
