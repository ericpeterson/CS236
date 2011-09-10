package project1;

enum State {
  START {
    public Transition nextTransition(int currentChar) {
      // The code to determine the next state after the start state is
      // in a separate method that is reused by other states.
      State nextState = doStart(currentChar);
      TokenType tokenType = null;
      return new Transition(nextState, tokenType);
    }
  },

  // New states go here.
  EOF {
    public Transition nextTransition (int currentChar) {
      // The EOF state is never exited.
      return null;
    }
  },

  UNDEFINED {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.UNDEFINED;
      return new Transition(nextState, tokenType);
    }
  };

  private static final int EOF_CHAR = -1;

  // End of the list of states.

  /**
   *  Determines which state to transition to on the given input character
   *  and determines which token type to eimt.
   *  This method must be implemented by all of the enum constants
   */
  public abstract Transition nextTransition(int currentChar);

  public static State doStart(int currentChar) {

    if (currentChar == EOF_CHAR) {
      return EOF;      
    } else {
      char ch = (char)currentChar;

      // debug
      /*
      System.out.println(ch);
      */
      // end debug
     
      // Check for specific characters here.
      return UNDEFINED;
    }
  }
}

