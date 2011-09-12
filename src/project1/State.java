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

  LEFT_PAREN {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.LEFT_PAREN;
      return new Transition(nextState, tokenType);
    }
  }, 

  START_IDENTIFIER {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType;
      State nextState;

      if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
        nextState = State.START_IDENTIFIER;
        tokenType = null;
      } else {
        nextState = doStart(currentChar);
        tokenType = TokenType.ID;
      }

      return new Transition(nextState, tokenType); 
    }
  },

  WHITESPACE {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType = TokenType.WHITESPACE; 
      State nextState = doStart(currentChar);
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
      State nextState = computeNextState(currentChar);
      return nextState;
    }
  }

  // helper function to compute the next state
  private static State computeNextState(int character) {
    State nextState = null;

    if (Character.isLetter(character)) {
      return State.START_IDENTIFIER; 
    }
    
    if (Character.isWhitespace(character)) {
      return State.WHITESPACE;
    }

    switch ((char)character) {
      case '\'':
        break;
      case ':':
//        nextState = State.COLON;
        break;
      case ',':
        break;
      case '(':
        nextState = State.LEFT_PAREN; 
        break;
      case ')':
        break;
      case '.':
        break;
      case '?':
        break;
      case '=':
        break;
      case '!':
        break;
      case '>':
        break;
      case '<':
        break;
      case '*':
        break;
      case '+':
        break;
      case '#':
        break;
      // deal with case 'EOF':
      //  break;
      // undefined
      default:
        break;
    }

    return nextState;
  }
}

