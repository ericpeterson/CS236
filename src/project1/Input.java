package project1;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Input {
  ArrayList<Integer> sequenceOfIntegers;
  int locationOfCurrentCharacter;
  private final static int EOS = -1; // end of stream

  // Constructor
  Input (BufferedReader reader) {
    int currentChar = EOS;
    sequenceOfIntegers = new ArrayList<Integer>();
    locationOfCurrentCharacter = 0;

    try {
      currentChar = reader.read();
    } catch (IOException exc) {
      System.out.println(exc);
    }

    while (currentChar != EOS) {
      sequenceOfIntegers.add(currentChar);

      try {
        currentChar = reader.read();
      } catch (IOException exc) {
        System.out.println(exc);
      }
    }
  }

  int getCurrentCharacter () {
    System.out.println("location: " + locationOfCurrentCharacter + ", size: " + sequenceOfIntegers.size());
    int currentCharacter = sequenceOfIntegers.get(locationOfCurrentCharacter);
    
    return currentCharacter;
  }

  void advance () {
    // only advance in the sequence if the current character
    // location is less than the size of the sequence itself.
    if (locationOfCurrentCharacter < (sequenceOfIntegers.size() - 1)) {
      locationOfCurrentCharacter += 1;
      System.out.println("Advance: " + locationOfCurrentCharacter);
    }
  }
}

