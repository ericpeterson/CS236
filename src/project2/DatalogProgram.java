//REQUIRED CLASS
package project2;

import project1.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DatalogProgram extends NT_Node {
  private LexicalAnalyzer lex;
  private SchemeList schemeList;
  private FactList factList;
  private RuleList ruleList;
  private QueryList queryList;

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

  /**
   *  This function determines whether a given TokenType is in the FIRST set of
   *  the non-terminal.
   *   
   *  @param tokenType the TokenType to be tested
   *  @return true if tokenType is in the FIRST set of the non-terminal. False
   *          otherwise.
   */
  public boolean inFirstOfDatalogProgram (TokenType tokenType) {
    return true; 
  }

  public DatalogProgram parseDatalogProgram (Token token) {
    System.out.println(token); 
    return null;
  }

  // this is the method that gets the ball rolling
  public void parse () {
    lex.analyze();

    for (Token token: lex) {
      parseDatalogProgram(token);
    }
  }

  public String toString () {
    String output = "";
    
    //output = schemeList.toString() + factList.toString() + ruleList.toString() + queryList.toString();

    return output;
  }
}
