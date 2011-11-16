package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

public class AttributeAttributePair extends PredicatePair {
  Value high_value;

  // Constructor
  AttributeAttributePair(Attribute attr, Value value) {
    attribute1 = attr;
  }

  public Value getValue() {
    return high_value;
  }

  public boolean eval(Tuple tuple) {
    // returns true if the value associated with attribute1in the tuple is equal to the high_value in this
  }
}
