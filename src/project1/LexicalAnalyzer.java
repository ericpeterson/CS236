//REQUIRED CLASS
package project1;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class LexicalAnalyzer implements Iterable<Token> {
  private ArrayList<Token> tokenList = new ArrayList<Token>();

  public LexicalAnalyzer(BufferedReader reader) throws IOException {
    State state = State.START;
    while (state != State.EOF) {
      int nextChar = reader.read();
      Transition transition = state.nextTransition(nextChar);
      state = transition.getNextState();

      // If the transition's output token is non-null, then there
      // is a complete token to emit. If it is null, then the value is
      // still being built, and there is nothing to emit.
      if (transition.getTokenType() != null) {
        // Do something with the token here.
      }

      // Deal with token values and line numbers here.
    }
  }

  // This allows code that uses a LexicalAnalyzer called lexer to do:
  // for (Token token: lexer)
  public Iterator<Token> iterator() {
    return tokenList.iterator();
  }
}

