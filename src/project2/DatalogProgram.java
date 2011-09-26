//REQUIRED CLASS
package project2;

import project1.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DatalogProgram extends NT_Node {
  private LexicalAnalyzer lex;

  public DatalogProgram (String file) {
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      lex = new LexicalAnalyzer(reader);     
    } catch (FileNotFoundException exc) {
      System.out.println(exc);
    } catch (IOException exc) {
      System.out.println(exc);
    } catch (Exception exc) {
      System.out.println(exc);
    }
  }

  // this is the method that gets the ball rolling
  public void parse () {
    lex.analyze();

    for (Token token: lex) {
      System.out.println(token.getValue());
    }
  
    System.out.println(lex);
  }

  public String toString () {
    return "";
  }
}
