//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an ouput string
//DO NOT ADD METHODS

package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Project1 {
	public static String body(String[] args) {
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(args[0]));
    } catch (FileNotFoundException exc) {
      System.out.println(exc);
    }

    try {
      LexicalAnalyzer lex = new LexicalAnalyzer(reader);
    } catch (IOException exc) {
      System.out.println(exc);
    }

		String output = "";

		return output;
	}
}

