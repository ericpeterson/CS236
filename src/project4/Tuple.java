//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;

public class Tuple {
  HashSet<AVPairs> attributeValuePairsSet;

  // Constructor
  Tuple() {
    // Create empty tuple
  }

  public void addAVPair (AVPairs newPair) {
    if (!attributeValuePairsSet.add(newPair)) {
      System.out.println("Warning: " + newPair + 
                         " not added to set.");
    }
  }

  public Tuple restrict(Schema schema) {
    // implement strict (see slides)
  }
}
