package parser;

public class ParserTester {
	public static void main (String[] args) {
		String userinput = "fd 50 bw 50";
		Parser myparser = new Parser();
		String language = "resources.languages/English";
		
		CommandNode myroot = myparser.generateCommandTree(userinput, language);
		//System.out.println(myroot.getNodeChildren().get(0).getCommandName());

	}
}
