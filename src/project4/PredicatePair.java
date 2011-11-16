//REQUIRED CLASS
package project2;

import project1.*;

public class PredicatePair {
  private Attribute attribute;

  // Constructor
  PredicatePair (Attribute attribute1) {
    
  }

  public Attribute getAttribute1() {
    return attribute;
  }

  public boolean eval(Tuple tuple) {
    // overridden in AVPair and APairs
  }
}
