package project2;

import project1.*;

/**
 *  This class represents the terminal nodes.
 */
public class T_Node extends NT_Node { 
  private String token_value;
  
  T_Node (Token token, String expected_terminal) throws ParseError {
    token_value = token.getValue();

    if (token_value.equals(expected_terminal)) {
      throw new ParseError(token); 
    }
  }

  public String toString() {
    return token_value;
  }
}
