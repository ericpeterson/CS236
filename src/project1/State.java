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

  // Comments
  BLOCK_COMMENT_START {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;

      if ((char)currentChar == '|') {
        nextState = State.BLOCK_COMMENT;
        tokenType = null; 
      } else if ((char)currentChar == '\n') {
        nextState = doStart(currentChar);
        tokenType = TokenType.COMMENT;
      } else if (currentChar == -1) {
        nextState = State.EOF;
        tokenType = TokenType.UNDEFINED;
      } else {
        nextState = State.COMMENT;
        tokenType = null;
      }
    
      return new Transition(nextState, tokenType);
    }
  },

  BLOCK_COMMENT {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;

      if ((char)currentChar == '|') {
        nextState = State.BLOCK_COMMENT_END;
        tokenType = null;
      } else if (currentChar == -1) {
        nextState = State.EOF;
        tokenType = TokenType.UNDEFINED;
      } else {
        nextState = State.BLOCK_COMMENT;
        tokenType = null;
      }
    
      return new Transition(nextState, tokenType);
    }
  },
  
  BLOCK_COMMENT_END {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;
    
      if ((char)currentChar == '#') { 
        nextState = State.COMMENT_END;
        tokenType = null;
      } else if (currentChar == -1) {
        nextState = State.EOF;
        tokenType = TokenType.UNDEFINED;
      } else {
        nextState = State.BLOCK_COMMENT;
        tokenType = null;
      }

      return new Transition(nextState, tokenType);
    }
  },

  COMMENT {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;
     
      if ((char)currentChar == '\n') {
        nextState = State.WHITESPACE;
        tokenType = TokenType.COMMENT;
      } else if (currentChar == -1) {
        nextState = State.EOF;
        tokenType = TokenType.UNDEFINED;
      } else {
        nextState = State.COMMENT;
        tokenType = null;
      }
      
      return new Transition(nextState, tokenType);
    }
  },

  COMMENT_END {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.COMMENT;
      
      return new Transition(nextState, tokenType);
    }
  },

  // Symbols
  COLON_DASH {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar); 
      TokenType tokenType;

      if ((char)currentChar == '-') {
        tokenType = TokenType.COLON_DASH;
      } else {
        tokenType = TokenType.COLON;
      }

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

  RIGHT_PAREN {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.RIGHT_PAREN;
      return new Transition(nextState, tokenType);
    }
  }, 

  PERIOD {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.PERIOD;
      return new Transition(nextState, tokenType);
    }
  },
  
  COMMA {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.COMMA;
      return new Transition(nextState, tokenType);
    }
  },

  Q_MARK {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.Q_MARK;
      return new Transition(nextState, tokenType);
    }
  },

  EQ {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.EQ;
      return new Transition(nextState, tokenType);
    }
  },
   
  NE {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;
      
      if ((char)currentChar == '=') {
        nextState = doStart(currentChar);
        tokenType = TokenType.NE;
      } else {
        nextState = doStart(currentChar);
        tokenType = TokenType.UNDEFINED;
      }

      return new Transition(nextState, tokenType);
    }
  },
  
  GE {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;
      
      if ((char)currentChar == '=') {
        nextState = doStart(currentChar);
        tokenType = TokenType.GE;
      } else {
        nextState = doStart(currentChar);
        tokenType = TokenType.GT;
      }

      return new Transition(nextState, tokenType);
    }
  },
  
  LE {
    public Transition nextTransition(int currentChar) {
      State nextState;
      TokenType tokenType;
      
      if ((char)currentChar == '=') {
        nextState = doStart(currentChar);
        tokenType = TokenType.LE;
      } else {
        nextState = doStart(currentChar);
        tokenType = TokenType.LT;
      }

      return new Transition(nextState, tokenType);
    }
  },

  MULTIPLY {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.MULTIPLY;
      return new Transition(nextState, tokenType);
    }
  },

  ADD {
    public Transition nextTransition(int currentChar) {
      State nextState = doStart(currentChar);
      TokenType tokenType = TokenType.ADD;
      return new Transition(nextState, tokenType);
    }
  },

  // Identifier
  START_IDENTIFIER {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType;
      State nextState;

      if (Character.isLetterOrDigit(currentChar)) {
        nextState = State.START_IDENTIFIER;
        tokenType = null;
      } else {
        nextState = doStart(currentChar);
        tokenType = TokenType.ID;
      }

      return new Transition(nextState, tokenType); 
    }
  },

  // Strings
  STRING_START {
    public Transition nextTransition (int currentChar) {
      TokenType tokenType;
      State nextState;

      if ((char)currentChar == '\'') {
        tokenType = null;
        nextState = State.STRING_END; 
      } else {
        tokenType = null;
        nextState = State.STRING;
      }

      return new Transition(nextState, tokenType);
    }
  },

  STRING {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType;
      State nextState;

      if ((char)currentChar == '\'') {
        tokenType = null;
        nextState = State.STRING_END;
      }/* else if ((char)currentChar == '\\') {
        tokenType = null;
        nextState = State.NEWLINE;
      }*/ else if (currentChar == -1) { // EOF case
        tokenType = TokenType.UNDEFINED; 
        nextState = State.EOF;
      } else {
        tokenType = null;
        nextState = State.STRING;
      }

      return new Transition(nextState, tokenType);
    } 
  },

  STRING_END {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType;
      State nextState;

      if ((char)currentChar == '\'') {
        tokenType = null;
        nextState = State.STRING;
      } else {
        tokenType = TokenType.STRING;
        nextState = doStart(currentChar);
      }

      return new Transition(nextState, tokenType);
    }
  },

  /* Handle the '\n' string case
  NEWLINE {
    public Transition nextTransition(int currentChar) {
      TokenType tokenType;
      State nextState;

      if ((char)currentChar = 'n') {
        tokenType = null;
        nextState = State.STRING; 
      } else if ((char)currentChar == '\'') {
        tokenType = TokenType.STRING;
        nextState = doStart(currentChar);
      } else {
        tokenType = null;
        nextState = State.STRING; 
      }
    }
  },
  */

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
    State nextState;

    if (currentChar == EOF_CHAR) {
      nextState = EOF;   
    } else {
      nextState = computeNextState(currentChar);
    }
  
    return nextState;
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
        nextState = State.STRING_START;
        break;
      case ':':
        nextState = State.COLON_DASH;
        break;
      case ',':
        nextState = State.COMMA;
        break;
      case '(':
        nextState = State.LEFT_PAREN; 
        break;
      case ')':
        nextState = State.RIGHT_PAREN; 
        break;
      case '.':
        nextState = State.PERIOD;
        break;
      case '?':
        nextState = State.Q_MARK;
        break;
      case '=':
        nextState = State.EQ;
        break;
      case '!':
        nextState = State.NE;
        break;
      case '>':
        nextState = State.GE;
        break;
      case '<':
        nextState = State.LE;
        break;
      case '*':
        nextState = State.MULTIPLY;
        break;
      case '+':
        nextState = State.ADD;
        break;
      case '#':
        nextState = State.BLOCK_COMMENT_START; 
        break;
      default:
        nextState = State.UNDEFINED;
        break;
    }

    return nextState;
  }
}

