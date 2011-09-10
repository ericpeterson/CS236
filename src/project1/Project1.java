//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an ouput string
//DO NOT ADD METHODS

package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Project1 {
	public static String body(String[] args) {
    String output = "";
    Iterator<Token> itr = null;
    LexicalAnalyzer lex = null;
  
    try {
      BufferedReader reader = new BufferedReader(new FileReader(args[0]));
      lex = new LexicalAnalyzer(reader);
    } catch (FileNotFoundException exc) {
      System.out.println(exc);
    } catch (IOException exc) {
      System.out.println(exc);
    }

    for (Token token: lex) {
      System.out.println(token);
    }

		return output;
	}
}

