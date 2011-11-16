//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.TreeSet;
public class Schema {
  // A Schema has an ordered set of attributes
  TreeSet<Attribute> attributeSet;
  Scheme originalScheme;
  // Constructor
  Schema (Scheme scheme) {
    originalScheme = scheme;
  }

  public void changeAttributeName(Attribute oldName, Attribute newName) {
    
  }

  public void restrict(Schema restricted) {
    
  }

  public Attribute getAttribute(int location) {
    // Get attribute at location in original scheme    
    return null; 
  }
}
