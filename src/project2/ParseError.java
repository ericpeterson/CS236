package project2;

import project1.*;

// TODO: remove inheritance of Exception if it is not useful
public class ParseError extends Exception {
  private Token offendingToken;

  public ParseError(Token token) {
    super();
    offendingToken = token;      
  }

  public String toString() {
    String output = "Failure!\n  " + 
      "(" + offendingToken.getType() + ",\"" + 
      offendingToken.getValue() + "\"," + 
      offendingToken.getLineNumber() + 
      ")";

    return output;
  }
}
 
