//REQUIRED CLASS
package project2;

import project1.*;

public class Scheme extends NT_Node {
  Scheme (Token token) throws ParseError {
    switch (token.getType()) {
      default:
        throw new ParseError(token);
    }
  }
}
