//REQUIRED CLASS
package project1;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class LexicalAnalyzer implements Iterable<Token> {
  private ArrayList<Token> tokenList = new ArrayList<Token>();
  private static final int LINE_FEED_ASCII = 0x0A; // 0x0A = decimal 10

  public LexicalAnalyzer(BufferedReader reader) throws IOException {
    State state = State.START;
    int currentLineNum = 0;     
    String currentValue = "";
 
    while (state != State.EOF) {
      int nextChar = reader.read();
      TokenType currentType = null;
      Transition transition = state.nextTransition(nextChar);

      state = transition.getNextState();

      // debug
      /*
      if (-1 != nextChar) {
        System.out.printf("%c: %d\n", (char)nextChar, nextChar);
      } else {
        System.out.println(nextChar);
      }
      */ 
      // end debug
      
      // If the transition's output token is non-null, then there
      // is a complete token to emit. If it is null, then the value is
      // still being built, and there is nothing to emit.
      if (transition.getTokenType() != null) {
        // Do something with the token here.
        Token currentToken = new Token (currentType, currentValue, currentLineNum);
        tokenList.add(currentToken);
        currentValue = ""; 
      }

      // Deal with token values and line numbers here.
      currentValue += (char)nextChar;

      // increment line number if the current character is a line feed
      if (nextChar == LINE_FEED_ASCII) {
        currentLineNum += 1; 
      }
    }
  }

  // This allows code that uses a LexicalAnalyzer called lexer to do:
  // for (Token token: lexer)
  public Iterator<Token> iterator() {
    return tokenList.iterator();
  }
}

