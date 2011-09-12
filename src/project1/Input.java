package project1;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Input {
  ArrayList<Integer> sequenceOfIntegers;
  int locationOfCurrentCharacter;
  int locationOfBeginningOfCurrentToken;
  int lineNumberOfCurrentCharacter;
  int lineNumberOfBeginningOfCurrentToken;

  // predefined constants
  private final static int EOS = -1; // end of stream
  private static final int LINE_FEED_ASCII = 0x0A; // 0x0A = decimal 10 = '\n'

  // Constructor
  Input (BufferedReader reader) {
    int currentChar = EOS;
    sequenceOfIntegers = new ArrayList<Integer>();
    locationOfCurrentCharacter = 0;
    locationOfBeginningOfCurrentToken = 0;
    lineNumberOfCurrentCharacter = 1;
    lineNumberOfBeginningOfCurrentToken = 1;

    try {
      currentChar = reader.read();
    } catch (IOException exc) {
      System.out.println(exc);
    }

    while (currentChar != EOS) {
      sequenceOfIntegers.add(currentChar);
      System.out.println(currentChar + " : " + (char)currentChar);
      try {
        currentChar = reader.read();
      } catch (IOException exc) {
        System.out.println(exc);
      }
    }
  }

  int getCurrentCharacter () {
    int currentCharacter = 0;
    
    if (locationOfCurrentCharacter < sequenceOfIntegers.size()) {
      currentCharacter = sequenceOfIntegers.get(locationOfCurrentCharacter);
    } 
    
    return currentCharacter;
  }

  int getLineNumber() {
    return lineNumberOfBeginningOfCurrentToken;
  }

  // returns the value of the current token
  String getValue() {
    int itr = 0;
    int endPosition = locationOfCurrentCharacter - 1;
    String value = "";
    
    for (itr = locationOfBeginningOfCurrentToken; itr <= endPosition; itr++) {
      value += (char)sequenceOfIntegers.get(itr).intValue(); 
    }

    return value; 
  }

  void markBeginningOfNextToken() {
    locationOfBeginningOfCurrentToken = locationOfCurrentCharacter;
    lineNumberOfBeginningOfCurrentToken = lineNumberOfCurrentCharacter;
  }

  void advance () {
    // only advance in the sequence if the current character
    // location is less than the size of the sequence itself.
    if (locationOfCurrentCharacter < (sequenceOfIntegers.size() - 1)) {
      locationOfCurrentCharacter += 1;
      
      if (sequenceOfIntegers.get(locationOfCurrentCharacter) == LINE_FEED_ASCII) {
        lineNumberOfCurrentCharacter += 1; 
      }
    } else { // do not advance if we are past the list's size
      locationOfCurrentCharacter = locationOfCurrentCharacter;
    }
  }
}

