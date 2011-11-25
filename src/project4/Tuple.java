//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;
import java.util.TreeSet;

public class Tuple implements Comparable<Tuple> {
  HashSet<AVPair> AVPairSet;

  // Constructor
  Tuple(FactList factList) {
    // Create empty tuple
  }

  public int compareTo(Tuple tuple) {
    return 0;
  }

  public TreeSet<AVPair> sort() {
    return null;
  }  

  /*public void addAVPair (AttributeValuePairs newPair) {
    if (!attributeValuePairsSet.add(newPair)) {
      System.out.println("Warning: " + newPair + 
                         " not added to set.");
    }
  }*/
/*
  public Tuple restrict(Schema schema) {
    // implement restrict (see slides)
    return null;
  }*/
}
