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
 
    while (currentState != State.EOF) {
      Transition transition = currentState.nextTransition(input.getCurrentCharacter());
      currentState = transition.getNextState();
      emit(transition.getTokenType());
      input.advance();
    }

    emit(TokenType.EOF); 
  }

  // emits the current token
  void emit(TokenType tokenType) {
    if (tokenType != null && tokenType != TokenType.WHITESPACE) {
      String value = input.getValue();

      if (value.equals("Schemes")) {
        tokenType = TokenType.SCHEMES;
      } else if (value.equals("Facts")) {
        tokenType = TokenType.FACTS;
      } else if (value.equals("Rules")) {
        tokenType = TokenType.RULES;
      } else if (value.equals("Queries")) {
        tokenType = TokenType.QUERIES;
      }
      
      Token token = new Token (tokenType, value, input.getLineNumber());
      tokenList.add(token);
      input.markBeginningOfNextToken();
    } else if (tokenType == TokenType.WHITESPACE) {
      input.markBeginningOfNextToken();
    } 
  }
  
  // This is for printing out
  // Total Tokens = ...
  public String toString () {
    return "Total Tokens = " + tokenList.size();
  }

  // This allows code that uses a LexicalAnalyzer called lexer to do:
  // for (Token token: lexer)
  public Iterator<Token> iterator() {
    return tokenList.iterator();
  }
}

