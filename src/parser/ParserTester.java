//package parser;
//
//import java.util.List;
//
//import interpreter.CommandTreeInterpreter;
//import turtle.Turtle;
//
//public class ParserTester {
//	public static void main (String[] args) {
//		String userinput = "TO :command [ :expr :exprr ] [ fd :expr fd :exprr ]";
//		Turtle turtle = new Turtle(null);
//		CommandTreeInterpreter tree = new CommandTreeInterpreter(turtle);
//		Parser myparser = new Parser(tree);
//		String language = "resources.languages/English";
//	}
//	
//	private void printTree(TreeGenerator myparser, CommandNode myRoot) {
//		myparser.printNode(myRoot);
//		while (myRoot.getNodeChildren().size()!=0) {
//			//myparser.printNode(myRoot.g)
//		}
//	}
//}
