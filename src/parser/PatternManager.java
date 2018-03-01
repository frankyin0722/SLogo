package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class PatternManager {
	
	 private List<Entry<String, Pattern>> mySymbols;
	 /**
	  * Create an empty parser.
	  */
	 public PatternManager () {
	        mySymbols = new ArrayList<>();
	    }
	 
	 /**
	  * Return the Pattern mapping 
	  */
	 public List<Entry<String, Pattern>> getPatterns(ResourceBundle syntax){
		 addPatterns(syntax);
		 return mySymbols;
	 }
	 
	 /**
	  * Adds the given resource file to this language's recognized types
	  */
	 public void addPatterns (ResourceBundle syntax) {
	     ResourceBundle resources = syntax;
	     Enumeration<String> iter = resources.getKeys();
	     while (iter.hasMoreElements()) {
	         String key = iter.nextElement();
	         String regex = resources.getString(key);
	         mySymbols.add(new SimpleEntry<>(key,
	                       Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
	     }
	 }
	 
	 /**
	  * Returns language's type associated with the given text if one exists 
	  */
	 public String getSymbol (String text) {
	     final String ERROR = "Error: NO MATCH FOUND";
	     for (Entry<String, Pattern> e : mySymbols) {
	         if (match(text, e.getValue())) {
	             return e.getKey();
	         }
	     }
	     throw new IllegalArgumentException(ERROR);
	 }
	    
	 /**
	  * Returns true if the given text matches the given regular expression pattern
	  */
	 
	 public boolean match (String text, Pattern regex) {
	        return regex.matcher(text).matches();
	 }
	 
}
