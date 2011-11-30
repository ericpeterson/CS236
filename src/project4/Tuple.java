//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.ArrayList;

public class Tuple implements Comparable<Tuple> {
  private ArrayList<Parameter> AVList;
 
  // Constructor
  Tuple(Fact fact) {
    AVList = new ArrayList<Parameter>(fact.getParameters());
  }

  // returns 0 if this.tuple == tuple
  //         
  public int compareTo(Tuple tuple) throws NullPointerException {
    if (null == tuple) {
      throw new NullPointerException();
    }
    
    int thisComparedToOther = 0;
    ArrayList<Parameter> thisAVList = this.getAVList();
    ArrayList<Parameter> otherAVList = tuple.getAVList();
    String thisValue = "";
    String otherValue = "";
  
    for (int index = 0; index < thisAVList.size(); index++) { 
      thisValue = thisAVList.get(index).getValue();
      otherValue = otherAVList.get(index).getValue();

      thisComparedToOther = thisValue.compareTo(otherValue);
      if (thisComparedToOther == 0) {
        continue;
      } else {
        return thisComparedToOther;
      }
    }

    // This should always be 0
    return thisComparedToOther;
  }

  public ArrayList<Parameter> getAVList() {
    return AVList;
  }

  public String toString() {
    return AVList.toString(); 
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
