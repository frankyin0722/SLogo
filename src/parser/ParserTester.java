package parser;

import java.util.List;

import interpreter.CommandTreeInterpreter;
import turtle.Turtle;

public class ParserTester {
	public static void main (String[] args) {
		//String userinput = "repeat [ fd sum sum :expr bk 50 50 ]";
		//String userinput = "repeat 4 [ repeat 4 [ fd 50 ] ] fd 50";
		//String userinput = "fd 50 repeat [ repeat 4 [ bk 50 fd 50 ] bk 50 bk 50 repeat 4 [ bk 4 ] ]";
		//String userinput = "DOTIMES [ :expr 50 ] [ SUM 20 80 SUM 20 30 ]";
		String userinput = "TO :command [ :expr :exprr ] [ fd :expr fd :exprr ]";
		Turtle turtle = new Turtle(null);
		CommandTreeInterpreter tree = new CommandTreeInterpreter(turtle);
		Parser myparser = new Parser(tree);
		String language = "resources.languages/English";
		
		//List<CommandNode> myroots = myparser.generateCommandTree(userinput, language);
		
		//
		/*for (int i = 0; i < myroots.size(); i++) {
			
		}*/
		//
		/*for (int i = 0 ; i < myroots.get(1).getNodeChildren().size(); i++) {
			
		}*/
		
		
		/*tree.interpretAllTrees(myroots);
		
		myparser.printNode(tree.getUserCommands().get(":command"));
		for (CommandNode child : tree.getUserCommands().get(":command").getNodeChildren()) {
			myparser.printNode(child);
			for (CommandNode childchild : child.getNodeChildren()) {
				myparser.printNode(childchild);
			}
		}*/
		
	}
	
	private void printTree(TreeGenerator myparser, CommandNode myRoot) {
		myparser.printNode(myRoot);
		while (myRoot.getNodeChildren().size()!=0) {
			//myparser.printNode(myRoot.g)
		}
	}
}

//package parser;
//
//import java.util.List;
//
//import interpreter.CommandTreeInterpreter;
//import turtle.Turtle;
//
//public class ParserTester {
//	public static void main (String[] args) {
//		//String userinput = "repeat [ fd sum sum :expr bk 50 50 ]";
//		//String userinput = "repeat 4 [ repeat 4 [ fd 50 ] ] fd 50";
//		//String userinput = "fd 50 repeat [ repeat 4 [ bk 50 fd 50 ] bk 50 bk 50 repeat 4 [ bk 4 ] ]";
//		//String userinput = "DOTIMES [ :expr 50 ] [ SUM 20 80 SUM 20 30 ]";
//		String userinput = "TO :command [ :expr :exprr ] [ fd :expr fd :exprr ]";
//		Turtle turtle = new Turtle(null);
//		CommandTreeInterpreter tree = new CommandTreeInterpreter(turtle);
//		Parser myparser = new Parser(tree);
//		String language = "resources.languages/English";
//		
//		List<CommandNode> myroots = myparser.generateCommandTree(userinput, language);
//		
//		//
//		/*for (int i = 0; i < myroots.size(); i++) {
//			
//		}*/
//		//
//		/*for (int i = 0 ; i < myroots.get(1).getNodeChildren().size(); i++) {
//			
//		}*/
//		
//		
//		tree.interpretAllTrees(myroots);
//		
//		myparser.printNode(tree.getUserCommands().get(":command"));
//		for (CommandNode child : tree.getUserCommands().get(":command").getNodeChildren()) {
//			myparser.printNode(child);
//			for (CommandNode childchild : child.getNodeChildren()) {
//				myparser.printNode(childchild);
//			}
//		}
//		
//	}
//	
//	private void printTree(TreeGenerator myparser, CommandNode myRoot) {
//		myparser.printNode(myRoot);
//		while (myRoot.getNodeChildren().size()!=0) {
//			//myparser.printNode(myRoot.g)
//		}
//	}
//}
