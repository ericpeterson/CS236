//REQUIRED CLASS
package project1;

public class Token {
  // Class variables
  private final TokenType type;
  private final String value;
  private final int lineNumber;

  // Constructor
  public Token(TokenType newType, String val, int lineNum) {
    type = newType;
    value = val;
    lineNumber = lineNum;  
  }
  
  // Access Methods
  public String getValue() {
    return value;
  }

  public TokenType getType() {
    return type;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  // Print method
  public String toString() {
    String output = "";
    // TODO: write method -> generate most of the required output
    return output;
  }
}

