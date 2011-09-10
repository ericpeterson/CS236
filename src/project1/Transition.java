package project1;

class Transition {
  // Class variables
  private final State nextState;
  private final TokenType tokenType;

  // Constructor
  Transition(State nextS, TokenType type) {
    nextState = nextS;
    tokenType = type;
  }

  // Access Methods
  public State getNextState() {
    return nextState;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  // Print method
  public String toString() {
    String output = "";
    // TODO: write toString()
    return output;
  }
}

