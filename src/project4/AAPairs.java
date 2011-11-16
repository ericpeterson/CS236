package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

public class AAPairs {
  HashSet<AttributeAttributePairs> aaPairs; 
 
  // Constructor
  AAPairs() {
    aaPairs = new HashSet<AttributeAttributePairs>();
  }

  public Iterator<AttributeAttributePairs> iterator() {
    return aaPairs.iterator();
  }
}
