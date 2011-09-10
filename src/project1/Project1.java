//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an ouput string
//DO NOT ADD METHODS

package project1;

public class Project1 {
	public static String body(String[] args) {
    BufferedReader reader = new BufferedReade(new FileReader(args[0]));

    try {
      LexicalAnalyzer lex = new LexicalAnalyzer(reader);
    } catch (IOException exc) {
      System.out.println("IOException: " + exc);
    }

		String output = "";

		return output;
	}
}

