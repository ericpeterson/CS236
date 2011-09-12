//REQUIRED CLASS
package project1;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class LexicalAnalyzer implements Iterable<Token> {
  private ArrayList<Token> tokenList = new ArrayList<Token>();
  private State currentState;
  private Input input;
  private static final int LINE_FEED_ASCII = 0x0A; // 0x0A = decimal 10

  public LexicalAnalyzer(BufferedReader reader) throws IOException {
    currentState = State.START;     
    input = new Input(reader); 
  }

  // helper class to perform the token generation
  public void analyze () {
    currentState = State.START;
    int count = 0;   
 
    while (currentState != State.EOF) {
      Transition transition = currentState.nextTransition(input.getCurrentCharacter());
      currentState = transition.getNextState();
      emit(transition.getTokenType());
      input.advance();

      count++;
      if (count > 16) { break; }
    }

    emit(TokenType.EOF); 
  }

  // emits the current token
  void emit(TokenType tokenType) {
    if (tokenType != null && tokenType != TokenType.WHITESPACE) {
      String value = input.getValue();

      switch (tokenType) {
        case SCHEMES:
          tokenType = TokenType.SCHEMES;
          break;
        case FACTS:
          tokenType = TokenType.FACTS;
          break;
        case RULES:
          tokenType = TokenType.RULES;
          break;
        case QUERIES:
          tokenType = TokenType.QUERIES; 
          break;
      }
 
      Token token = new Token (tokenType, value, input.getLineNumber());
      tokenList.add(token);
      input.markBeginningOfNextToken();
    } 
  }

  // This allows code that uses a LexicalAnalyzer called lexer to do:
  // for (Token token: lexer)
  public Iterator<Token> iterator() {
    return tokenList.iterator();
  }
}

