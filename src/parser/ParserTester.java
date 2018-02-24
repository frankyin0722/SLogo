package parser;

import java.util.List;

import interpreter.CommandTreeInterpreter;

public class ParserTester {
	public static void main (String[] args) {
		//String userinput = "repeat [ fd sum sum :expr bk 50 50 ]";
		//String userinput = "repeat 4 [ repeat 4 [ fd 50 ] ] fd 50";
		//String userinput = "fd 50 repeat [ repeat 4 [ bk 50 fd 50 ] bk 50 bk 50 repeat 4 [ bk 4 ] ]";
		String userinput = "SUM 20 10 SUM 30 40";
		Parser myparser = new Parser();
		String language = "resources.languages/English";
		
		List<CommandNode> myroots = myparser.generateCommandTree(userinput, language);
		/*for (int i = 0; i < myroots.size(); i++) {
			System.out.println(myroots.get(i).getCommandName());
		}*/
		//System.out.println(myroots.get(1).getNodeChildren().size());
		/*for (int i = 0 ; i < myroots.get(1).getNodeChildren().size(); i++) {
			System.out.println(myroots.get(i).getNodeChildren().get(i).getCommandName());
		}*/
		
		CommandTreeInterpreter tree = new CommandTreeInterpreter();
		tree.interpretAllTrees(myroots);
		for (int i = 0; i < myroots.size(); i++) {
			System.out.println(myroots.get(i).getNodeValue());
		}
	}
	
	private void printTree(CommandNode myRoot) {
		
	}
}
