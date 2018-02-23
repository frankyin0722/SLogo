package parser;

import java.util.List;

public class ParserTester {
	public static void main (String[] args) {
		//String userinput = "repeat [ fd sum sum :expr bk 50 50 ]";
		//String userinput = "repeat 4 [ repeat 4 [ fd 50 ] ] fd 50";
		String userinput = "repeat 4 [ bk 50 fd 50 ] bk 50";
		Parser myparser = new Parser();
		String language = "resources.languages/English";
		
		List<CommandNode> myroots = myparser.generateCommandTree(userinput, language);
		System.out.println(myroots.get(0).getCommandName());
		//myparser.printNode(myroots.get(0));
		System.out.println(myroots.get(1).getCommandName());
		//System.out.println(myroot.getNodeChildren().get(0).getCommandName());

	}
}
