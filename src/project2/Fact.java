//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class Fact {
  private ArrayList<Token> tokenList;

  Fact() {
    tokenList = new ArrayList<Token>();
  }

  public void add(Token token) {
    tokenList.add(token);
  }

  public ArrayList<Token> getTokenList () {
    return tokenList;
  }

  public String toString() {
    String output = "";
    
    for (Token token: tokenList) {
      output += token.getValue();
    }

    return output;
  }
}
