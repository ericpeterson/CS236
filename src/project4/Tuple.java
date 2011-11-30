//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.TreeSet;
import java.util.ArrayList;

public class Tuple implements Comparable<Tuple> {
  private ArrayList<Parameter> AVList;

  // Constructor
  Tuple(Fact fact) {
    AVList = new ArrayList<Parameter>(fact.getParameters());
  }

  public int compareTo(Tuple tuple) {
    return 0;
  }

  public TreeSet<Parameter> sort() {
    return null;
  }  

  public ArrayList<Parameter> getAVList() {
    return AVList;
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
