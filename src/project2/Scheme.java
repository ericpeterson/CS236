//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class Scheme extends NT_Node {
  private ArrayList<Token> tokenList;

  Scheme () {
    tokenList = new ArrayList<Token>();
  }

  public void add (Token token) {
    tokenList.add(token);
  }
  
  public ArrayList<Token> getTokenList() {
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
